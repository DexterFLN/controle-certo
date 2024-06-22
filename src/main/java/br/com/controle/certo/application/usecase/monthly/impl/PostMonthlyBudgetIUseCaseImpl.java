package br.com.controle.certo.application.usecase.monthly.impl;

import br.com.controle.certo.application.gateway.monthly.PostMonthlyBudgetGateway;
import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.application.usecase.monthly.GetMonthlyBudgetCurrentMonthUseCase;
import br.com.controle.certo.application.usecase.monthly.PostMonthlyBudgetIUseCase;
import br.com.controle.certo.infrastructure.entrypoint.handler.MonthlyBudgetException;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestMonthlyBudget;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseMonthlyBudget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class PostMonthlyBudgetIUseCaseImpl implements PostMonthlyBudgetIUseCase {

    @Autowired
    private PostMonthlyBudgetGateway monthlyBudgetGateway;
    @Autowired
    private GetMonthlyBudgetCurrentMonthUseCase currentMonthUseCase;
    @Autowired
    private GetUserGateway userGateway;

    @Override
    public void postMonthlyBudget(String userDocument, RequestMonthlyBudget body) {
        ResponseMonthlyBudget result = currentMonthUseCase.getMonthlyBudgetCurrentMonth(userDocument);
        if (nonNull(result)) {
            throw new MonthlyBudgetException("Orçamento para o mês corrente já foi criado.");
        }
        monthlyBudgetGateway.postMonthlyBudget(body, userDocument);
    }
}