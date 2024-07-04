package br.com.controle.certo.application.gateway.monthly;

import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestMonthlyBudget;

public interface PostMonthlyBudgetGateway {

    void postMonthlyBudget(RequestMonthlyBudget body, String document);
}