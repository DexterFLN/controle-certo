package br.com.controle.certo.infrastructure.gateway.monthly;

import br.com.controle.certo.application.gateway.monthly.PostMonthlyBudgetGateway;
import br.com.controle.certo.infrastructure.entrypoint.handler.CategoryException;
import br.com.controle.certo.infrastructure.entrypoint.handler.MonthlyBudgetException;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestItemBudget;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestMonthlyBudget;
import br.com.controle.certo.infrastructure.repository.impl.DbCategoryRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbItemBudgetRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbMonthlyBudgetRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import br.com.controle.certo.infrastructure.repository.model.DbItemBudget;
import br.com.controle.certo.infrastructure.repository.model.DbMonthlyBudget;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.controle.certo.infrastructure.mappers.MonthlyBudgetMapper.monthlyBudgetEntityToDbMonthlyBudget;

@Component
public class PostMonthlyBudgetGatewayImpl implements PostMonthlyBudgetGateway {

    @Autowired
    private DbMonthlyBudgetRepository repository;
    @Autowired
    private DbCategoryRepository categoryRepository;
    @Autowired
    private DbUserRepository userRepository;
    @Autowired
    private DbItemBudgetRepository itemRepository;

    @Override
    public void postMonthlyBudget(RequestMonthlyBudget body, String userDocument) {
        List<Integer> collect = body.getItemBudgetList().stream().map(RequestItemBudget::getDbCategory).toList();
        List<DbCategory> dbCategoryList = categoryRepository.getAllCategoryByDocument(userDocument);
        List<Integer> categoriesNotFound = new ArrayList<>();

        collect.forEach(m -> {
            if (dbCategoryList.stream().noneMatch(f -> f.getIdCategory().equals(m))) {
                categoriesNotFound.add(m);
            }

            if (!categoriesNotFound.isEmpty()) {
                throw new CategoryException("Categoria " + categoriesNotFound.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(",")) + " não existe para este usuário");
            }
        });

        if (dbCategoryList.isEmpty()) {
            throw new MonthlyBudgetException("O orçamento deve ter itens de categoria adicionados");
        }

        DbUser user = userRepository.getUserByDocument(userDocument);
        DbMonthlyBudget response = repository.save(monthlyBudgetEntityToDbMonthlyBudget(body, user));

        List<DbItemBudget> dbItemBudgetStream = body.getItemBudgetList().stream().map(m ->
                DbItemBudget.builder()
                        .budgetTarget(Boolean.FALSE)
                        .categoryPercentage(m.getCategoryPercentage())
                        .dbCategory(DbCategory.builder().idCategory(m.getDbCategory()).build())
                        .dbMonthlyBudget(response)
                        .build()
        ).toList();

        itemRepository.saveAll(dbItemBudgetStream);
    }
}