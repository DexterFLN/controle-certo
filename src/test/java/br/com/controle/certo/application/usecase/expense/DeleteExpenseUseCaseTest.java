package br.com.controle.certo.application.usecase.expense;

import br.com.controle.certo.infrastructure.repository.impl.DbExpenseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DeleteExpenseUseCaseTest {

    @Mock
    private DbExpenseRepository expenseRepository;

    @InjectMocks
    private DeleteExpenseUseCase deleteExpenseUseCase;

    @Test
    void testDeleteExpenseById() {
        MockitoAnnotations.openMocks(this);
        Integer idExpense = 1;
        String userDocument = "123456789";
        boolean recursive = false;
        String expenseType = "type";
        int month = 7;
        int year = 2024;

        deleteExpenseUseCase.deleteExpenseById(idExpense, userDocument, recursive, expenseType, month, year);

        verify(expenseRepository).deleteExpenseById(idExpense, month, year);
    }
}
