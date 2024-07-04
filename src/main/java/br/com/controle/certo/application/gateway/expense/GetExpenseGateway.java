package br.com.controle.certo.application.gateway.expense;

import br.com.controle.certo.domain.entities.ExpenseEntity;

import java.util.List;

public interface GetExpenseGateway {
    List<ExpenseEntity> getAllExpenseByUserDocument(String userDocument);

    List<ExpenseEntity> getExpenseCurrentMonth(String userDocument, int month, int year);

    ExpenseEntity getExpenseById(Integer idExpense, String userDocument);
}
