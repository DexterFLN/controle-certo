package br.com.controle.certo.application.usecase.monthly.impl;

import br.com.controle.certo.application.gateway.monthly.GetMonthlyBudgetGateway;
import br.com.controle.certo.domain.entities.ItemBudgetEntity;
import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseItemBudget;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetMonthlyBudgetLastMonthUseCaseImplTest {

    @Mock
    private GetMonthlyBudgetGateway getMonthlyBudgetGateway;

    @InjectMocks
    private GetMonthlyBudgetLastMonthUseCaseImpl getMonthlyBudgetLastMonthUseCase;

    private MonthlyBudgetEntity monthlyBudgetEntity;
    private String userDocument;

    @BeforeEach
    public void setUp() {
        userDocument = "12345678901";

        monthlyBudgetEntity = MonthlyBudgetEntity.builder()
                .idMonthlyBudget(1)
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .dhExclude(null)
                .monthlyIncome(5000.0)
                .monthlyReference("06/2023")
                .itemBudgetEntityList(Collections.emptyList())
                .build();
    }

    @Test
    public void testGetMonthlyBudgetLastMonth() {
        when(getMonthlyBudgetGateway.getLastMonthlyBudget(userDocument)).thenReturn(monthlyBudgetEntity);

        ResponseMonthlyBudget responseMonthlyBudget = getMonthlyBudgetLastMonthUseCase.getMonthlyBudgetLastMonth(userDocument);

        verify(getMonthlyBudgetGateway, times(1)).getLastMonthlyBudget(userDocument);

        assertEquals(monthlyBudgetEntity.getIdMonthlyBudget(), responseMonthlyBudget.getIdMonthlyBudget());
        assertEquals(monthlyBudgetEntity.getDhCreate(), responseMonthlyBudget.getDhCreate());
        assertEquals(monthlyBudgetEntity.getDhUpdate(), responseMonthlyBudget.getDhUpdate());
        assertEquals(monthlyBudgetEntity.getDhExclude(), responseMonthlyBudget.getDhExclude());
        assertEquals(monthlyBudgetEntity.getMonthlyIncome(), responseMonthlyBudget.getMonthlyIncome());
        assertEquals(monthlyBudgetEntity.getMonthlyReference(), responseMonthlyBudget.getMonthlyReference());
        assertEquals(monthlyBudgetEntity.getItemBudgetEntityList().size(), responseMonthlyBudget.getItemBudgetEntityList().size());
    }
}
