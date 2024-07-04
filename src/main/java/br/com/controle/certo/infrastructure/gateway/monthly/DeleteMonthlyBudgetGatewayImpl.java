package br.com.controle.certo.infrastructure.gateway.monthly;

import br.com.controle.certo.application.gateway.monthly.DeleteMonthlyBudgetGateway;
import br.com.controle.certo.infrastructure.repository.impl.DbMonthlyBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteMonthlyBudgetGatewayImpl implements DeleteMonthlyBudgetGateway {
    @Autowired
    private DbMonthlyBudgetRepository repository;

    @Override
    public void deleteMonthlyBudget(Integer idMonthlyBudget) {
        repository.deleteMonthlyBudgetById(idMonthlyBudget);
    }
}
