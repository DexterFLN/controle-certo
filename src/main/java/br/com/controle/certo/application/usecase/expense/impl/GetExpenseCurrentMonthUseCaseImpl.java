package br.com.controle.certo.application.usecase.expense.impl;

import br.com.controle.certo.application.gateway.expense.GetExpenseGateway;
import br.com.controle.certo.application.usecase.expense.GetExpenseCurrentMonthUseCase;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseExpensive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static br.com.controle.certo.application.mapper.ExpenseUseCaseMapper.listExpenseEntityToListResponseExpense;

@Component
public class GetExpenseCurrentMonthUseCaseImpl implements GetExpenseCurrentMonthUseCase {

    @Autowired
    private GetExpenseGateway expenseGateway;

    @Override
    public List<ResponseExpensive> getExpenseCurrentMonth(String userDocument) {
        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();

        List<ExpenseEntity> response = expenseGateway.getExpenseCurrentMonth(userDocument, month, year);
        return listExpenseEntityToListResponseExpense(response);
    }
}
