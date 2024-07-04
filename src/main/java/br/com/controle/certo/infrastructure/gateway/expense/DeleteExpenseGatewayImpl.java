package br.com.controle.certo.infrastructure.gateway.expense;

import br.com.controle.certo.application.gateway.expense.DeleteExpenseGateway;
import br.com.controle.certo.infrastructure.repository.impl.DbExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DeleteExpenseGatewayImpl implements DeleteExpenseGateway {
    @Autowired
    private DbExpenseRepository repository;

    @Override
    public void deleteExpenseById(Integer idExpense, int month, int year) {
        repository.deleteExpenseById(idExpense, month, year);
    }

    @Override
    public void deleteExpenseByUuId(String uuidExpense, int month, int year) {
        repository.deleteExpenseByUuid(uuidExpense, month, year);
    }
}
