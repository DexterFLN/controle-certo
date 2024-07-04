package br.com.controle.certo.infrastructure.gateway.expense;

import br.com.controle.certo.application.gateway.expense.GetExpenseGateway;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbExpenseRepository;
import br.com.controle.certo.infrastructure.repository.model.DbExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static br.com.controle.certo.infrastructure.mappers.ExpenseMapper.dbExpenseToExpenseEntity;
import static br.com.controle.certo.infrastructure.mappers.ExpenseMapper.listDbExpenseToListExpenseEntity;

@Component
public class GetExpenseGatewayImpl implements GetExpenseGateway {

    @Autowired
    private DbExpenseRepository repository;

    @Override
    public List<ExpenseEntity> getAllExpenseByUserDocument(String userDocument) {
        List<DbExpense> response = repository.getAllExpenseByUserDocument(userDocument);
        return listDbExpenseToListExpenseEntity(response);
    }

    @Override
    public List<ExpenseEntity> getExpenseCurrentMonth(String userDocument, int month, int year) {
        List<DbExpense> response = repository.getExpenseCurrentMonthByUserDocument(userDocument, month, year);
        return listDbExpenseToListExpenseEntity(response);
    }

    @Override
    public ExpenseEntity getExpenseById(Integer idExpense, String userDocument) {
        DbExpense response = repository.getExpenseByIdExpenseAndUserDocument(idExpense, userDocument);
        return dbExpenseToExpenseEntity(response);
    }
}
