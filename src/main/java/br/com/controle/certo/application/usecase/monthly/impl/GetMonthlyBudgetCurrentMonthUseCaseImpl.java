package br.com.controle.certo.application.usecase.monthly.impl;

import br.com.controle.certo.application.gateway.monthly.GetMonthlyBudgetGateway;
import br.com.controle.certo.application.usecase.monthly.GetMonthlyBudgetCurrentMonthUseCase;
import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseMonthlyBudget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GetMonthlyBudgetCurrentMonthUseCaseImpl implements GetMonthlyBudgetCurrentMonthUseCase {

    @Autowired
    private GetMonthlyBudgetGateway gateway;
    @Override
    public ResponseMonthlyBudget getMonthlyBudgetCurrentMonth(String userDocument) {
        LocalDate currentDate = LocalDate.now();
        int currentMonth =  currentDate.getMonthValue();
        int currentYear =  currentDate.getYear();
        MonthlyBudgetEntity monthlyBudgetCurrentMonth = gateway.getMonthlyBudgetCurrentMonth(userDocument, currentMonth, currentYear);

        return null;
    }
}