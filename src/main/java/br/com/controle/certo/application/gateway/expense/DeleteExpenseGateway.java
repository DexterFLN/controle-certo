package br.com.controle.certo.application.gateway.expense;

import java.time.LocalDateTime;

public interface DeleteExpenseGateway {
    void deleteExpenseById(Integer idExpense, LocalDateTime dhCreate);
    void deleteExpenseByUuId(String uuidExpense, LocalDateTime dhCreate);
}
