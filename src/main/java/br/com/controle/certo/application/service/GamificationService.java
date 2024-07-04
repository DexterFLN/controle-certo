package br.com.controle.certo.application.service;

import br.com.controle.certo.infrastructure.repository.impl.DbExpenseRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbMonthlyBudgetRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbExpense;
import br.com.controle.certo.infrastructure.repository.model.DbItemBudget;
import br.com.controle.certo.infrastructure.repository.model.DbMonthlyBudget;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class GamificationService {

    @Autowired
    private DbMonthlyBudgetRepository monthlyBudgetRepository;
    @Autowired
    private DbUserRepository userRepository;
    @Autowired
    private DbExpenseRepository expenseRepository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Scheduled(cron = "0 0 0 1 * ?")
    public void updateXpForPreviousMonth() {
        YearMonth previousMonth = YearMonth.now().minusMonths(1);
        String previousMonthReference = previousMonth.format(DateTimeFormatter.ofPattern("MM/yyyy"));

        int page = 0;
        Page<DbUser> userPage;

        do {
            Pageable pageable = PageRequest.of(page, 100);
            userPage = userRepository.findAll(pageable);
            for (DbUser user : userPage) {
                executorService.submit(() -> processUserBudget(user, previousMonthReference));
            }
            page++;
        } while (userPage.hasNext());
    }

    @Transactional
    private void processUserBudget(DbUser user, String previousMonthReference) {
        DbMonthlyBudget budget = monthlyBudgetRepository.getDbMonthlyBudgetCurrentMonth(user.getDocumentNumber(), previousMonthReference);

        if (budget != null) {
            YearMonth yearMonth = YearMonth.parse(previousMonthReference, DateTimeFormatter.ofPattern("MM/yyyy"));
            LocalDateTime startDate = yearMonth.atDay(1).atStartOfDay();
            LocalDateTime endDate = yearMonth.plusMonths(1).atDay(1).atStartOfDay();

            int xpGained = calculateXp(budget, user, startDate, endDate);
            synchronized (user) {
                int currentXp = user.getXpUser() == null ? 0 : user.getXpUser();
                if (xpGained > 0) {
                    user.setXpUser(currentXp + xpGained);
                    updateUserLevel(user);
                    userRepository.save(user);
                    monthlyBudgetRepository.save(budget);
                }
            }
        }
    }

    private int calculateXp(DbMonthlyBudget budget, DbUser user, LocalDateTime startDate, LocalDateTime endDate) {
        int xp = 0;
        List<DbExpense> expenses = expenseRepository.findByDbUserAndMonth(user, startDate, endDate);
        Map<Integer, Double> expenseByCategory = expenses.stream()
                .collect(Collectors.groupingBy(expense -> expense.getDbCategory().getIdCategory(),
                        Collectors.summingDouble(DbExpense::getExpenseValue)));

        for (DbItemBudget item : budget.getDbItemBudget()) {
            Double totalExpense = expenseByCategory.getOrDefault(item.getDbCategory().getIdCategory(), 0.0);
            if (isNull(item.getExperienceGranted()) || !item.getExperienceGranted()) {
                if (totalExpense <= item.getExpenseValue()) {
                    xp += 10;
                    item.setBudgetTarget(true);
                } else {
                    item.setBudgetTarget(false);
                }
                item.setExperienceGranted(true);
            }
        }
        return xp;
    }

    private void updateUserLevel(DbUser user) {
        int totalXp = user.getXpUser();
        int currentLevel = user.getLvlUser() == null ? 0 : user.getLvlUser();
        int levelsGained = totalXp / 30;
        int remainingXp = totalXp % 30;

        user.setLvlUser(currentLevel + levelsGained);
        user.setXpUser(remainingXp);
    }
}
