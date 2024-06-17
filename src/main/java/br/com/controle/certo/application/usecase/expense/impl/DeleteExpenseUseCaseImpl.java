package br.com.controle.certo.application.usecase.expense.impl;

import br.com.controle.certo.application.gateway.expense.DeleteExpenseGateway;
import br.com.controle.certo.application.gateway.expense.GetExpenseGateway;
import br.com.controle.certo.application.usecase.expense.DeleteExpenseUseCase;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.infrastructure.entrypoint.handler.ExpenseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Component
public class DeleteExpenseUseCaseImpl implements DeleteExpenseUseCase {

    @Autowired
    private GetExpenseGateway getExpenseGateway;
    @Autowired
    private DeleteExpenseGateway deleteExpenseGateway;

    @Override
    public void deleteExpenseById(Integer idExpense, String userDocument, Boolean recursive) {
        ExpenseEntity response = getExpenseGateway.getExpenseById(idExpense, userDocument);
        validateDhCreateIsBeforeCurrentDate(response.getDhCreate());

        if (nonNull(response)) {
            if (recursive) {
                deleteExpenseGateway.deleteExpenseByUuId(response.getUuidExpense(), response.getDhCreate());
            } else {
                deleteExpenseGateway.deleteExpenseById(response.getIdExpense(), response.getDhCreate());
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
