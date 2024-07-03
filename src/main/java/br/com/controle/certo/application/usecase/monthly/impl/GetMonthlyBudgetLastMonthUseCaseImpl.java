package br.com.controle.certo.application.usecase.monthly.impl;

import br.com.controle.certo.application.gateway.monthly.GetMonthlyBudgetGateway;
import br.com.controle.certo.application.usecase.monthly.GetMonthlyBudgetLastMonthUseCase;
import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseMonthlyBudget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.controle.certo.application.mapper.MonthlyBudgetUseCaseMapper.monthlyBudgetEntityToResponseMonthlyBudgetEntity;

@Component
public class GetMonthlyBudgetLastMonthUseCaseImpl implements GetMonthlyBudgetLastMonthUseCase {

    @Autowired
    private GetMonthlyBudgetGateway gateway;

    @Override
    public ResponseMonthlyBudget getMonthlyBudgetLastMonth(String userDocument) {
        MonthlyBudgetEntity result = gateway.getLastMonthlyBudget(userDocument);
        return monthlyBudgetEntityToResponseMonthlyBudgetEntity(result);
    }
}
