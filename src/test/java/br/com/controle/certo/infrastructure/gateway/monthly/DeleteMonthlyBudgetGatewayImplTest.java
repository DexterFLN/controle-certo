package br.com.controle.certo.infrastructure.gateway.monthly;

import br.com.controle.certo.infrastructure.repository.impl.DbMonthlyBudgetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class DeleteMonthlyBudgetGatewayImplTest {

    @InjectMocks
    private DeleteMonthlyBudgetGatewayImpl deleteMonthlyBudgetGateway;

    @Mock
    private DbMonthlyBudgetRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteMonthlyBudget() {
        Integer idMonthlyBudget = 1;

        deleteMonthlyBudgetGateway.deleteMonthlyBudget(idMonthlyBudget);

        verify(repository, times(1)).deleteMonthlyBudgetById(idMonthlyBudget);
    }
}
