package br.com.controle.certo.application.gateway.expense;

import java.time.LocalDateTime;

public interface DeleteExpenseGateway {
    void deleteExpenseById(Integer idExpense, int month, int year);
    void deleteExpenseByUuId(String uuidExpense, int month, int year);
}
