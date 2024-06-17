package br.com.controle.certo.application.gateway.expense;

import br.com.controle.certo.domain.entities.ExpenseEntity;

import java.util.List;

public interface PostExpenseGateway {
    void saveExpense(List<ExpenseEntity> body);
}
