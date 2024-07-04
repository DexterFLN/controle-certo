package br.com.controle.certo.application.usecase.expense;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseExpensive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class GetExpenseByIdUseCaseTest {
    @Mock
    private GetExpenseByIdUseCase getExpenseByIdUseCase;
    @Test
    void testGetExpenseById() {
        ResponseExpensive expectedResponse = ResponseExpensive.builder()
                .idExpense(1)
                .uuidDespesa("uuid123")
                .expenseDescription("Test expense")
                .expenseValue(100.0)
                .currentInstallment(1)
                .totalInstallment(10)
                .expenseType("Type")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .responseCategory(new ResponseCategory())
                .build();
        when(getExpenseByIdUseCase.getExpenseById(anyInt(), anyString()))
                .thenReturn(expectedResponse);

        ResponseExpensive actualResponse = getExpenseByIdUseCase.getExpenseById(1, "userDocument");

        assertEquals(expectedResponse, actualResponse);
    }
}
