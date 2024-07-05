package br.com.controle.certo.infrastructure.gateway.expense;

import br.com.controle.certo.infrastructure.repository.impl.DbExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DeleteExpenseGatewayImplTest {

    @Mock
    private DbExpenseRepository repository;

    @InjectMocks
    private DeleteExpenseGatewayImpl deleteExpenseGateway;

    @Test
    public void testDeleteExpenseById() {
        Integer idExpense = 1;
        int month = 6;
        int year = 2023;

        deleteExpenseGateway.deleteExpenseById(idExpense, month, year);

        verify(repository, times(1)).deleteExpenseById(idExpense, month, year);
    }

    @Test
    public void testDeleteExpenseByUuid() {
        String uuidExpense = "123e4567-e89b-12d3-a456-426614174000";
        int month = 6;
        int year = 2023;

        deleteExpenseGateway.deleteExpenseByUuId(uuidExpense, month, year);

        verify(repository, times(1)).deleteExpenseByUuid(uuidExpense, month, year);
    }
}
