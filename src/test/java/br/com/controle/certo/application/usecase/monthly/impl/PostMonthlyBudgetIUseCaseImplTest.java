package br.com.controle.certo.application.usecase.monthly.impl;

import br.com.controle.certo.application.gateway.monthly.PostMonthlyBudgetGateway;
import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.application.usecase.monthly.GetMonthlyBudgetCurrentMonthUseCase;
import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.handler.MonthlyBudgetException;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestMonthlyBudget;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseMonthlyBudget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostMonthlyBudgetIUseCaseImplTest {

    @Mock
    private PostMonthlyBudgetGateway postMonthlyBudgetGateway;

    @Mock
    private GetMonthlyBudgetCurrentMonthUseCase getMonthlyBudgetCurrentMonthUseCase;

    @Mock
    private GetUserGateway getUserGateway;

    @InjectMocks
    private PostMonthlyBudgetIUseCaseImpl postMonthlyBudgetIUseCase;

    private RequestMonthlyBudget requestMonthlyBudget;
    private String userDocument;

    @BeforeEach
    public void setUp() {
        userDocument = "12345678901";

        requestMonthlyBudget = RequestMonthlyBudget.builder()
                .monthlyIncome(5000.0)
                .monthlyReference("07/2023")
                .itemBudgetList(Collections.emptyList())
                .build();
    }

    @Test
    public void testPostMonthlyBudget_Success() {
        when(getMonthlyBudgetCurrentMonthUseCase.getMonthlyBudgetCurrentMonth(userDocument, 7, 2023))
                .thenReturn(null);

        postMonthlyBudgetIUseCase.postMonthlyBudget(userDocument, requestMonthlyBudget);

        verify(getMonthlyBudgetCurrentMonthUseCase, times(1))
                .getMonthlyBudgetCurrentMonth(userDocument, 7, 2023);
        verify(postMonthlyBudgetGateway, times(1))
                .postMonthlyBudget(requestMonthlyBudget, userDocument);
    }

    @Test
    public void testPostMonthlyBudget_AlreadyExists() {
        ResponseMonthlyBudget existingBudget = ResponseMonthlyBudget.builder().build();
        when(getMonthlyBudgetCurrentMonthUseCase.getMonthlyBudgetCurrentMonth(userDocument, 7, 2023))
                .thenReturn(existingBudget);

        assertThrows(MonthlyBudgetException.class, () -> {
            postMonthlyBudgetIUseCase.postMonthlyBudget(userDocument, requestMonthlyBudget);
        });

        verify(getMonthlyBudgetCurrentMonthUseCase, times(1))
                .getMonthlyBudgetCurrentMonth(userDocument, 7, 2023);
        verify(postMonthlyBudgetGateway, never())
                .postMonthlyBudget(any(), any());
    }
}
