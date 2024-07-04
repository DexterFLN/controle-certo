package br.com.controle.certo.application.gateway.expense;

import br.com.controle.certo.domain.entities.ExpenseEntity;

public interface PutExpenseGateway {
    void updateExpenseById(ExpenseEntity expenseEntity);
    void updateExpenseByUuId(ExpenseEntity expenseEntity);
}
