package br.com.controle.certo.application.gateway.monthly;

import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;

public interface GetMonthlyBudgetGateway {
    MonthlyBudgetEntity getMonthlyBudgetCurrentMonth(String document, int month, int year);
}