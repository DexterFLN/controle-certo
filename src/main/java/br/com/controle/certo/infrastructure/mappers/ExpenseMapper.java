package br.com.controle.certo.infrastructure.mappers;

import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.infrastructure.repository.model.DbExpense;

import java.util.List;

public class ExpenseMapper {

    public static ExpenseEntity dbExpenseToExpenseEntity(DbExpense dbExpense) {
        return ExpenseEntity.builder()
                .idExpense(dbExpense.getIdExpense())
                .uuidExpense(dbExpense.getUuidExpense())
                .expenseDescription(dbExpense.getExpenseDescription())
                .expenseValue(dbExpense.getExpenseValue())
                .currentInstallment(dbExpense.getCurrentInstallment())
                .totalInstallment(dbExpense.getTotalInstallment())
                .recurringExpense(dbExpense.getRecurringExpense())
                .dhCreate(dbExpense.getDhCreate())
                .dhUpdate(dbExpense.getDhUpdate())
                .categoryEntity(CategoryEntity.builder()
                        .idCategory(dbExpense.getDbCategory().getIdCategory())
                        .categoryName(dbExpense.getDbCategory().getCategoryName())
                        .categoryDescription(dbExpense.getDbCategory().getCategoryDescription())
                        .dhCreate(dbExpense.getDbCategory().getDhCreate())
                        .dhUpdate(dbExpense.getDbCategory().getDhUpdate())
                        .build())
                .build();
    }
    public static List<ExpenseEntity> listDbExpenseToListExpenseEntity(List<DbExpense> dbExpenseList) {
        return dbExpenseList.stream().map(m ->
                ExpenseEntity.builder()
                        .idExpense(m.getIdExpense())
                        .uuidExpense(m.getUuidExpense())
                        .expenseDescription(m.getExpenseDescription())
                        .expenseValue(m.getExpenseValue())
                        .currentInstallment(m.getCurrentInstallment())
                        .totalInstallment(m.getTotalInstallment())
                        .recurringExpense(m.getRecurringExpense())
                        .dhCreate(m.getDhCreate())
                        .dhUpdate(m.getDhUpdate())
                        .categoryEntity(CategoryEntity.builder()
                                .idCategory(m.getDbCategory().getIdCategory())
                                .categoryName(m.getDbCategory().getCategoryName())
                                .categoryDescription(m.getDbCategory().getCategoryDescription())
                                .dhCreate(m.getDbCategory().getDhCreate())
                                .dhUpdate(m.getDbCategory().getDhUpdate())
                                .build())
                        .build()
        ).toList();
    }
}
