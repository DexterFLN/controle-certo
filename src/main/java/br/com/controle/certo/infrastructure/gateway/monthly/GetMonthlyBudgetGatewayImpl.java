package br.com.controle.certo.infrastructure.gateway.monthly;

import br.com.controle.certo.application.gateway.monthly.GetMonthlyBudgetGateway;
import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbMonthlyBudgetRepository;
import br.com.controle.certo.infrastructure.repository.model.DbMonthlyBudget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.controle.certo.infrastructure.mappers.MonthlyBudgetMapper.dbMonthlyBudgetToMonthlyBudgetEntity;

@Component
public class GetMonthlyBudgetGatewayImpl implements GetMonthlyBudgetGateway {

    @Autowired
    private DbMonthlyBudgetRepository repository;

    @Override
    public MonthlyBudgetEntity getMonthlyBudgetCurrentMonth(String userDocument, int month, int year) {
        DbMonthlyBudget response = repository.getDbMonthlyBudgetCurrentMonth(userDocument, month, year);
        return dbMonthlyBudgetToMonthlyBudgetEntity(response);
    }
}