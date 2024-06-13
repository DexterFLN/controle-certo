package br.com.controle.certo.application.usecase.expense;

public interface DeleteExpenseUseCase {
    void deleteExpenseById(Integer idExpense, String userDocument, Boolean recursive);
}