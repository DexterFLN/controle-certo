package br.com.controle.certo.application.usecase.expense.impl;

import br.com.controle.certo.application.gateway.category.GetCategoryGateway;
import br.com.controle.certo.application.gateway.expense.PostExpenseGateway;
import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.application.usecase.expense.PostExpenseUseCase;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.handler.CategoryException;
import br.com.controle.certo.infrastructure.entrypoint.handler.UserException;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class PostExpenseUseCaseImpl implements PostExpenseUseCase {

    @Autowired
    private GetUserGateway userGateway;
    @Autowired
    private GetCategoryGateway categoryGateway;
    @Autowired
    private PostExpenseGateway postExpenseGateway;

    @Override
    public void postExpense(RequestExpense body, String document) {
        UserEntity user = userGateway.getUserByUserDocument(document);
        CategoryEntity categoryEntity = validateUserAndCategory(body, document, user);

        List<ExpenseEntity> resultList = new ArrayList<>(emptyList());

        if (body.getRecurringExpense()) {
            expenseRecurring(body, user, categoryEntity, resultList);
        } else {
            expenseInstallment(body, user, categoryEntity, resultList);
        }

        postExpenseGateway.saveExpense(resultList);
    }


    private void expenseRecurring(RequestExpense body, UserEntity user, CategoryEntity categoryEntity, List<ExpenseEntity> resultList) {
        LocalDateTime dhCreateExpense = LocalDateTime.now();

        int monthCurrent = dhCreateExpense.getMonthValue();
        int yearCurrent = dhCreateExpense.getYear();
        String uuidExpense = UUID.randomUUID().toString();

        for (int i = 0; i < 12; i++) {
            if (monthCurrent > 12) {
                monthCurrent = 1;
                yearCurrent++;
            }
            LocalDateTime currentDh = verifyDhCreate(monthCurrent, yearCurrent);
            createExpense(body, user, categoryEntity, resultList, currentDh, null, uuidExpense);
            monthCurrent++;
        }
    }

    private void expenseInstallment(RequestExpense body, UserEntity user, CategoryEntity categoryEntity, List<ExpenseEntity> expenseEntityList) {
        LocalDateTime dhCreateExpense = LocalDateTime.now();

        Integer totalInstallment = body.getTotalInstallment();
        Integer currentInstallment = body.getCurrentInstallment();
        String uuidExpense = UUID.randomUUID().toString();

        if (nonNull(totalInstallment) && nonNull(currentInstallment)) {
            int expenseTotal = totalInstallment - currentInstallment;
            int monthCurrent = dhCreateExpense.getMonthValue();
            int yearCurrent = dhCreateExpense.getYear();

            for (int i = 0; i < expenseTotal + 1; i++) {
                if (monthCurrent > 12) {
                    monthCurrent = 1;
                    yearCurrent++;
                }
                LocalDateTime currentDh = verifyDhCreate(monthCurrent, yearCurrent);
                createExpense(body, user, categoryEntity, expenseEntityList, currentDh, currentInstallment, uuidExpense);
                monthCurrent++;
                currentInstallment++;
            }
        } else {
            createExpense(body, user, categoryEntity, expenseEntityList, dhCreateExpense, null, null);
        }
    }

    private void createExpense(RequestExpense body, UserEntity user, CategoryEntity
            categoryEntity, List<ExpenseEntity> expenseEntityList, LocalDateTime currentDh, Integer currentInstallment, String uuidExpense) {
        ExpenseEntity build = ExpenseEntity.builder()
                .uuidExpense(uuidExpense)
                .expenseDescription(body.getExpenseDescription())
                .expenseValue(body.getExpenseValue())
                .currentInstallment(nonNull(currentInstallment) ? currentInstallment : null)
                .totalInstallment(body.getTotalInstallment())
                .recurringExpense(body.getRecurringExpense())
                .dhCreate(currentDh)
                .categoryEntity(categoryEntity)
                .userEntity(user)
                .build();

        expenseEntityList.add(build);
    }

    private LocalDateTime verifyDhCreate(int monthCurrent, int yearCurrent) {
        LocalTime localTime = LocalTime.now();
        int dayOfMonth = LocalDate.now().getDayOfMonth();
        return LocalDateTime.of(yearCurrent, monthCurrent, dayOfMonth, localTime.getHour(), localTime.getMinute());
    }

    private CategoryEntity validateUserAndCategory(RequestExpense body, String document, UserEntity user) {
        if (isNull(user)) {
            throw new UserException("Usuário não encontrado.");
        }
        CategoryEntity categoryEntity = categoryGateway.getCategoryById(document, body.getDbCategory());
        if (isNull(categoryEntity)) {
            throw new CategoryException("Categoria não encontrada");
        }
        return categoryEntity;
    }
}
