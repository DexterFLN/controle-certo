package br.com.controle.certo.application.usecase.expense;

import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestPutExpense;

public interface PutExpenseUseCase {
    void updateExpenseById(Integer idExpense, String userDocument, Boolean recursive, RequestPutExpense body);
}