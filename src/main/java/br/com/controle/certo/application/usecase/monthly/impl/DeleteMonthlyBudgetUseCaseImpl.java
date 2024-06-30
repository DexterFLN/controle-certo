package br.com.controle.certo.application.usecase.monthly.impl;

import br.com.controle.certo.application.gateway.monthly.DeleteMonthlyBudgetGateway;
import br.com.controle.certo.application.gateway.monthly.GetMonthlyBudgetGateway;
import br.com.controle.certo.application.usecase.monthly.DeleteMonthlyBudgetUseCase;
import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.handler.MonthlyBudgetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DeleteMonthlyBudgetUseCaseImpl implements DeleteMonthlyBudgetUseCase {

    @Autowired
    private DeleteMonthlyBudgetGateway deleteMonthlyBudgetGateway;
    @Autowired
    private GetMonthlyBudgetGateway getMonthlyBudgetGateway;

    @Override
    public void deleteMonthlyBudget(String userDocument, Integer idMonthlyBudget) {
        MonthlyBudgetEntity response = getMonthlyBudgetGateway.getMonthlyBudget(userDocument, idMonthlyBudget);

        if (Objects.isNull(response)) {
            throw new MonthlyBudgetException("Orçamento não encontrado.");
        }
        deleteMonthlyBudgetGateway.deleteMonthlyBudget(idMonthlyBudget);
    }
}
