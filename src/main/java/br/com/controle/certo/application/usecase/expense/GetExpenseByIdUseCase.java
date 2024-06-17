package br.com.controle.certo.application.usecase.expense;

import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseExpensive;

public interface GetExpenseByIdUseCase {
    ResponseExpensive getExpenseById(Integer idExpense, String userDocument);
}
