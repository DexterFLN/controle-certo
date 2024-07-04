package br.com.controle.certo.application.usecase.monthly;

import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestMonthlyBudget;

public interface PostMonthlyBudgetIUseCase {
    void postMonthlyBudget(String document, RequestMonthlyBudget body);
}
