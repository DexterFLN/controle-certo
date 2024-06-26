package br.com.controle.certo.application.usecase.expense;

import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseExpensive;

import java.util.List;

public interface GetExpenseUseCase {
    List<ResponseExpensive> getAllExpenseByDocument(String document);
}
