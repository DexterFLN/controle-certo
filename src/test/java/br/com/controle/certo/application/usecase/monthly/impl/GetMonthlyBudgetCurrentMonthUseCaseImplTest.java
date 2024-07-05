package br.com.controle.certo.application.usecase.monthly.impl;

import br.com.controle.certo.application.gateway.monthly.GetMonthlyBudgetGateway;
import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseMonthlyBudget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetMonthlyBudgetCurrentMonthUseCaseImplTest {

    @Mock
    private GetMonthlyBudgetGateway getMonthlyBudgetGateway;

    @InjectMocks
    private GetMonthlyBudgetCurrentMonthUseCaseImpl getMonthlyBudgetCurrentMonthUseCase;

    private MonthlyBudgetEntity monthlyBudgetEntity;
    private String userDocument;
    private int month;
    private int year;

    @BeforeEach
    public void setUp() {
        userDocument = "12345678901";
        month = 7;
        year = 2023;

        monthlyBudgetEntity = MonthlyBudgetEntity.builder()
                .idMonthlyBudget(1)
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .dhExclude(null)
                .monthlyIncome(5000.0)
                .monthlyReference("07/2023")
                .itemBudgetEntityList(Collections.emptyList())
                .build();
    }

    @Test
    public void testGetMonthlyBudgetCurrentMonth() {
        when(getMonthlyBudgetGateway.getMonthlyBudgetCurrentMonth(userDocument, month, year)).thenReturn(monthlyBudgetEntity);

        ResponseMonthlyBudget responseMonthlyBudget = getMonthlyBudgetCurrentMonthUseCase.getMonthlyBudgetCurrentMonth(userDocument, month, year);

        verify(getMonthlyBudgetGateway, times(1)).getMonthlyBudgetCurrentMonth(userDocument, month, year);

        assertNotNull(responseMonthlyBudget);
        assertEquals(monthlyBudgetEntity.getIdMonthlyBudget(), responseMonthlyBudget.getIdMonthlyBudget());
        assertEquals(monthlyBudgetEntity.getDhCreate(), responseMonthlyBudget.getDhCreate());
        assertEquals(monthlyBudgetEntity.getDhUpdate(), responseMonthlyBudget.getDhUpdate());
        assertEquals(monthlyBudgetEntity.getDhExclude(), responseMonthlyBudget.getDhExclude());
        assertEquals(monthlyBudgetEntity.getMonthlyIncome(), responseMonthlyBudget.getMonthlyIncome());
        assertEquals(monthlyBudgetEntity.getMonthlyReference(), responseMonthlyBudget.getMonthlyReference());
        assertEquals(monthlyBudgetEntity.getItemBudgetEntityList().size(), responseMonthlyBudget.getItemBudgetEntityList().size());
    }
}
