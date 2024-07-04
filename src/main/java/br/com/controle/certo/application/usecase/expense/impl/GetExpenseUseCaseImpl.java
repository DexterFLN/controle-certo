package br.com.controle.certo.application.usecase.expense.impl;

import br.com.controle.certo.application.gateway.expense.GetExpenseGateway;
import br.com.controle.certo.application.usecase.expense.GetExpenseUseCase;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseExpensive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.controle.certo.application.mapper.ExpenseUseCaseMapper.listExpenseEntityToListResponseExpense;

@Component
public class GetExpenseUseCaseImpl implements GetExpenseUseCase {

    @Autowired
    private GetExpenseGateway expenseGateway;

    @Override
    public List<ResponseExpensive> getAllExpenseByDocument(String userDocument) {
        List<ExpenseEntity> response = expenseGateway.getAllExpenseByUserDocument(userDocument);
        return listExpenseEntityToListResponseExpense(response);
    }
}
