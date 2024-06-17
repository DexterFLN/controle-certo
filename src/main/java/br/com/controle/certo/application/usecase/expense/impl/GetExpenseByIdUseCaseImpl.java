package br.com.controle.certo.application.usecase.expense.impl;

import br.com.controle.certo.application.usecase.expense.GetExpenseByIdUseCase;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseExpensive;
import org.springframework.stereotype.Component;

@Component
public class GetExpenseByIdUseCaseImpl implements GetExpenseByIdUseCase {
    @Override
    public ResponseExpensive getExpenseById(Integer idExpense, String userDocument) {
        return null;
    }
}
