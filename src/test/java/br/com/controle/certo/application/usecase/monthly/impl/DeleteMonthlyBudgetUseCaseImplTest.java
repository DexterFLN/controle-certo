package br.com.controle.certo.application.usecase.monthly.impl;

import br.com.controle.certo.application.gateway.monthly.DeleteMonthlyBudgetGateway;
import br.com.controle.certo.application.gateway.monthly.GetMonthlyBudgetGateway;
import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.handler.MonthlyBudgetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteMonthlyBudgetUseCaseImplTest {

    @Mock
    private DeleteMonthlyBudgetGateway deleteMonthlyBudgetGateway;

    @Mock
    private GetMonthlyBudgetGateway getMonthlyBudgetGateway;

    @InjectMocks
    private DeleteMonthlyBudgetUseCaseImpl deleteMonthlyBudgetUseCase;

    private String userDocument;
    private Integer idMonthlyBudget;
    private MonthlyBudgetEntity monthlyBudgetEntity;

    @BeforeEach
    public void setUp() {
        userDocument = "12345678901";
        idMonthlyBudget = 1;

        monthlyBudgetEntity = MonthlyBudgetEntity.builder()
                .idMonthlyBudget(idMonthlyBudget)
                .build();
    }

    @Test
    public void testDeleteMonthlyBudget_Success() {
        when(getMonthlyBudgetGateway.getMonthlyBudget(userDocument, idMonthlyBudget))
                .thenReturn(monthlyBudgetEntity);

        deleteMonthlyBudgetUseCase.deleteMonthlyBudget(userDocument, idMonthlyBudget);

        verify(getMonthlyBudgetGateway, times(1))
                .getMonthlyBudget(userDocument, idMonthlyBudget);
        verify(deleteMonthlyBudgetGateway, times(1))
                .deleteMonthlyBudget(idMonthlyBudget);
    }

    @Test
    public void testDeleteMonthlyBudget_NotFound() {
        when(getMonthlyBudgetGateway.getMonthlyBudget(userDocument, idMonthlyBudget))
                .thenReturn(null);

        assertThrows(MonthlyBudgetException.class, () -> {
            deleteMonthlyBudgetUseCase.deleteMonthlyBudget(userDocument, idMonthlyBudget);
        });

        verify(getMonthlyBudgetGateway, times(1))
                .getMonthlyBudget(userDocument, idMonthlyBudget);
        verify(deleteMonthlyBudgetGateway, never())
                .deleteMonthlyBudget(anyInt());
    }
}
