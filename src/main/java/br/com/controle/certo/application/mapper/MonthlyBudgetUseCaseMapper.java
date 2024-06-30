package br.com.controle.certo.application.mapper;

import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseItemBudget;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseMonthlyBudget;

import static java.util.Objects.nonNull;


public class MonthlyBudgetUseCaseMapper {

    public static ResponseMonthlyBudget monthlyBudgetEntityToResponseMonthlyBudgetEntity(MonthlyBudgetEntity body) {
        return nonNull(body) ? ResponseMonthlyBudget.builder()
                .idMonthlyBudget(body.getIdMonthlyBudget())
                .dhCreate(body.getDhCreate())
                .dhUpdate(body.getDhUpdate())
                .dhExclude(body.getDhExclude())
                .monthlyIncome(body.getMonthlyIncome())
                .monthlyReference(body.getMonthlyReference())
                .itemBudgetEntityList(body.getItemBudgetEntityList().stream().map(m ->
                        ResponseItemBudget.builder()
                                .idItemBudget(m.getIdItemBudget())
                                .budgetTarget(m.getBudgetTarget())
                                .expenseValue(m.getExpenseValue())
                                .dhCreate(m.getDhCreate())
                                .dhUpdate(m.getDhUpdate())
                                .dbCategory(ResponseCategory.builder()
                                        .idCategory(m.getDbCategory().getIdCategory())
                                        .categoryDescription(m.getDbCategory().getCategoryDescription())
                                        .categoryName(m.getDbCategory().getCategoryName())
                                        .dhCreate(m.getDbCategory().getDhCreate())
                                        .build())
                                .build()).toList())
                .build() : null;
    }
}
