package br.com.controle.certo.infrastructure.gateway.expense;

import br.com.controle.certo.application.gateway.expense.PutExpenseGateway;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbCategoryRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbExpenseRepository;
import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PutExpenseGatewayImpl implements PutExpenseGateway {
    @Autowired
    private DbExpenseRepository repository;

    @Autowired
    private DbCategoryRepository categoryRepository;

    @Override
    public void updateExpenseById(ExpenseEntity expenseEntity) {
        LocalDateTime dhCreate = expenseEntity.getDhCreate();
        repository.updateExpenseById(expenseEntity.getIdExpense(),
                expenseEntity.getExpenseDescription(),
                expenseEntity.getExpenseValue(),
                DbCategory.builder().idCategory(expenseEntity.getCategoryEntity().getIdCategory()).build(),
                dhCreate);
    }

    @Override
    public void updateExpenseByUuId(ExpenseEntity expenseEntity) {
        LocalDateTime dhCreate = expenseEntity.getDhCreate();
        repository.updateExpenseByUuid(expenseEntity.getUuidExpense(),
                expenseEntity.getExpenseDescription(),
                expenseEntity.getExpenseValue(),
                DbCategory.builder().idCategory(expenseEntity.getCategoryEntity().getIdCategory()).build(),
                dhCreate);
    }
}
