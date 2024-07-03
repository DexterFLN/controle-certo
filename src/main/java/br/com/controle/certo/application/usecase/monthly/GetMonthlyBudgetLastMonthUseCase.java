package br.com.controle.certo.application.usecase.monthly;

import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseMonthlyBudget;

public interface GetMonthlyBudgetLastMonthUseCase {
    ResponseMonthlyBudget getMonthlyBudgetLastMonth(String userDocument);
}
