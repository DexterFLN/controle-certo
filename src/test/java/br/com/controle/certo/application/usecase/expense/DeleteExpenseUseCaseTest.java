package br.com.controle.certo.application.usecase.expense;

import br.com.controle.certo.application.gateway.expense.DeleteExpenseGateway;
import br.com.controle.certo.application.gateway.expense.GetExpenseGateway;
import br.com.controle.certo.application.usecase.expense.impl.DeleteExpenseUseCaseImpl;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteExpenseUseCaseTest {

    @Mock
    private DeleteExpenseGateway deleteExpenseGateway;

    @Mock
    private GetExpenseGateway getExpenseGateway;

    @InjectMocks
    private DeleteExpenseUseCaseImpl deleteExpenseUseCase;

    private ExpenseEntity expense;

    @BeforeEach
    void setup() {
        expense = ExpenseEntity.builder()
                .idExpense(1)
                .uuidExpense("uuid")
                .dhCreate(LocalDateTime.now())
                .build();
    }

    @Test
    void testDeleteExpenseById() {
        Integer idExpense = 1;
        String userDocument = "123456789";
        Boolean recursive = false;
        String expenseType = "normal"; // Não é recorrente
        int month = 7;
        int year = 2024;

        when(getExpenseGateway.getExpenseById(idExpense, userDocument)).thenReturn(expense);

        deleteExpenseUseCase.deleteExpenseById(idExpense, userDocument, recursive, expenseType, month, year);

        verify(deleteExpenseGateway, times(1)).deleteExpenseById(idExpense, month, year);
        verify(deleteExpenseGateway, never()).deleteExpenseByUuId(anyString(), anyInt(), anyInt());
    }

    @Test
    void testDeleteExpenseByUuid() {
        Integer idExpense = 1;
        String userDocument = "123456789";
        Boolean recursive = true;
        String expenseType = "recorrente"; // É recorrente
        int month = 7;
        int year = 2024;

        when(getExpenseGateway.getExpenseById(idExpense, userDocument)).thenReturn(expense);

        deleteExpenseUseCase.deleteExpenseById(idExpense, userDocument, recursive, expenseType, month, year);

        verify(deleteExpenseGateway, times(1)).deleteExpenseByUuId(expense.getUuidExpense(), month, year);
        verify(deleteExpenseGateway, never()).deleteExpenseById(anyInt(), anyInt(), anyInt());
    }
}
