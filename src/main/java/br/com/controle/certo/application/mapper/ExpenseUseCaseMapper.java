package br.com.controle.certo.application.mapper;

import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestPutExpense;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseExpensive;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class ExpenseUseCaseMapper {

    public static List<ResponseExpensive> listExpenseEntityToListResponseExpense(List<ExpenseEntity> expenseEntityList) {
        return expenseEntityList.stream().map(m ->
                ResponseExpensive.builder()
                        .idExpense(m.getIdExpense())
                        .uuidDespesa(m.getUuidExpense())
                        .expenseDescription(m.getExpenseDescription())
                        .expenseValue(m.getExpenseValue())
                        .currentInstallment(m.getCurrentInstallment())
                        .totalInstallment(m.getTotalInstallment())
                        .recurringExpense(m.getRecurringExpense())
                        .dhCreate(m.getDhCreate())
                        .dhUpdate(m.getDhUpdate())
                        .responseCategory(
                                ResponseCategory.builder()
                                        .idCategory(m.getCategoryEntity().getIdCategory())
                                        .categoryName(m.getCategoryEntity().getCategoryName())
                                        .categoryDescription(m.getCategoryEntity().getCategoryDescription())
                                        .dhCreate(m.getCategoryEntity().getDhCreate())
                                        .dhUpdate(m.getCategoryEntity().getDhUpdate())
                                        .build())
                        .build()
        ).toList();
    }

    public static ExpenseEntity requestPutExpenseToExpenseEntity(RequestPutExpense body, ExpenseEntity response) {
        return ExpenseEntity.builder()
                .idExpense(nonNull(body.getIdDespesa()) ? body.getIdDespesa() : response.getIdExpense())
                .uuidExpense(nonNull(body.getUuidDespesa()) ? body.getUuidDespesa() : response.getUuidExpense())
                .expenseDescription(nonNull(body.getExpenseDescription()) ? body.getExpenseDescription() : response.getExpenseDescription())
                .expenseValue(nonNull(body.getExpenseValue()) ? body.getExpenseValue() : response.getExpenseValue())
                .dhCreate(response.getDhCreate())
                .categoryEntity(CategoryEntity.builder().idCategory(nonNull(body.getResponseCategory().getIdCategory()) ? body.getResponseCategory().getIdCategory() : response.getCategoryEntity().getIdCategory()).build())
                .build();
    }
}