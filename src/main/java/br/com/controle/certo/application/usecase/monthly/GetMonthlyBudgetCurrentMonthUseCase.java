package br.com.controle.certo.application.usecase.monthly;

import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseMonthlyBudget;

public interface GetMonthlyBudgetCurrentMonthUseCase {
    ResponseMonthlyBudget getMonthlyBudgetCurrentMonth(String userDocument, int month, int year);
}
