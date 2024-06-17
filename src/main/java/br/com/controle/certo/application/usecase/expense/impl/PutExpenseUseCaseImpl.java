package br.com.controle.certo.application.usecase.expense.impl;

import br.com.controle.certo.application.gateway.category.GetCategoryGateway;
import br.com.controle.certo.application.gateway.expense.GetExpenseGateway;
import br.com.controle.certo.application.gateway.expense.PutExpenseGateway;
import br.com.controle.certo.application.usecase.expense.PutExpenseUseCase;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.infrastructure.entrypoint.handler.ExpenseException;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestPutExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static br.com.controle.certo.application.mapper.ExpenseUseCaseMapper.requestPutExpenseToExpenseEntity;
import static java.util.Objects.nonNull;

@Component
public class PutExpenseUseCaseImpl implements PutExpenseUseCase {
    @Autowired
    private GetExpenseGateway getExpenseGateway;
    @Autowired
    private PutExpenseGateway putExpenseGateway;
    @Autowired
    private GetCategoryGateway getCategoryGateway;

    @Override
    public void updateExpenseById(Integer idExpense, String userDocument, Boolean recursive, RequestPutExpense body) {
        ExpenseEntity response = getExpenseGateway.getExpenseById(idExpense, userDocument);
        validateCategory(userDocument, body);
        validateDhCreateIsBeforeCurrentDate(response.getDhCreate());

        if (nonNull(response)) {
            if (recursive) {
                putExpenseGateway.updateExpenseByUuId(requestPutExpenseToExpenseEntity(body, response));
            } else {
                putExpenseGateway.updateExpenseById(requestPutExpenseToExpenseEntity(body, response));
            }
        } else {
            throw new ExpenseException("Despesa não encontrada.");
        }
    }

    private void validateCategory(String userDocument, RequestPutExpense body) {
        if (body.getResponseCategory() != null && body.getResponseCategory().getIdCategory() != null) {
            CategoryEntity result = getCategoryGateway.getCategoryById(userDocument, body.getResponseCategory().getIdCategory());
            if (result == null) {
                throw new ExpenseException("Categoria não existe para este usuário.");
            }
        }
    }

    private void validateDhCreateIsBeforeCurrentDate(LocalDateTime dhCreateExpense) {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.getMonthValue() > dhCreateExpense.getMonthValue() && currentDate.getYear() >= dhCreateExpense.getYear()) {
            throw new ExpenseException("Despesas dos meses anteriores não podem ser alteradas.");
        }
    }
}
