package br.com.controle.certo.application.usecase.monthly.impl;

import br.com.controle.certo.application.gateway.monthly.GetMonthlyBudgetGateway;
import br.com.controle.certo.application.usecase.monthly.GetMonthlyBudgetCurrentMonthUseCase;
import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseMonthlyBudget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static br.com.controle.certo.application.mapper.MonthlyBudgetUseCaseMapper.monthlyBudgetEntityToResponseMonthlyBudgetEntity;

@Component
public class GetMonthlyBudgetCurrentMonthUseCaseImpl implements GetMonthlyBudgetCurrentMonthUseCase {

    @Autowired
    private GetMonthlyBudgetGateway gateway;

    @Override
    public ResponseMonthlyBudget getMonthlyBudgetCurrentMonth(String userDocument, int month, int year) {
        MonthlyBudgetEntity result = gateway.getMonthlyBudgetCurrentMonth(userDocument, month, year);
        return monthlyBudgetEntityToResponseMonthlyBudgetEntity(result);
    }
}
