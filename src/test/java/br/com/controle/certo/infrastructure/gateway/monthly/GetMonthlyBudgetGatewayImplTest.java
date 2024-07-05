package br.com.controle.certo.infrastructure.gateway.monthly;

import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbMonthlyBudgetRepository;
import br.com.controle.certo.infrastructure.repository.model.DbMonthlyBudget;
import br.com.controle.certo.infrastructure.mappers.MonthlyBudgetMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class GetMonthlyBudgetGatewayImplTest {

    @InjectMocks
    private GetMonthlyBudgetGatewayImpl getMonthlyBudgetGateway;

    @Mock
    private DbMonthlyBudgetRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMonthlyBudgetCurrentMonth() {
        String userDocument = "user123";
        int month = 7;
        int year = 2024;
        String monthlyReference = String.format("%02d/%d", month, year);

        DbMonthlyBudget dbMonthlyBudget = new DbMonthlyBudget();
        dbMonthlyBudget.setIdMonthlyBudget(1);
        dbMonthlyBudget.setMonthlyReference(monthlyReference);
        dbMonthlyBudget.setDbItemBudget(new ArrayList<>()); // Inicializando a lista

        when(repository.getDbMonthlyBudgetCurrentMonth(userDocument, monthlyReference)).thenReturn(dbMonthlyBudget);

        MonthlyBudgetEntity result = getMonthlyBudgetGateway.getMonthlyBudgetCurrentMonth(userDocument, month, year);

        assertNotNull(result);
        assertEquals(1, result.getIdMonthlyBudget());
        assertEquals(monthlyReference, result.getMonthlyReference());
        verify(repository, times(1)).getDbMonthlyBudgetCurrentMonth(userDocument, monthlyReference);
    }

    @Test
    public void testGetMonthlyBudget() {
        String userDocument = "user123";
        Integer idMonthlyBudget = 1;

        DbMonthlyBudget dbMonthlyBudget = new DbMonthlyBudget();
        dbMonthlyBudget.setIdMonthlyBudget(idMonthlyBudget);
        dbMonthlyBudget.setDbItemBudget(new ArrayList<>()); // Inicializando a lista

        when(repository.getDbMonthlyBudgetByUserDocumentAndId(userDocument, idMonthlyBudget)).thenReturn(dbMonthlyBudget);

        MonthlyBudgetEntity result = getMonthlyBudgetGateway.getMonthlyBudget(userDocument, idMonthlyBudget);

        assertNotNull(result);
        assertEquals(idMonthlyBudget, result.getIdMonthlyBudget());
        verify(repository, times(1)).getDbMonthlyBudgetByUserDocumentAndId(userDocument, idMonthlyBudget);
    }

    @Test
    public void testGetLastMonthlyBudget() {
        String userDocument = "user123";

        DbMonthlyBudget dbMonthlyBudget = new DbMonthlyBudget();
        dbMonthlyBudget.setIdMonthlyBudget(1);
        dbMonthlyBudget.setDbItemBudget(new ArrayList<>()); // Inicializando a lista

        when(repository.findMostRecentMonthlyBudgetByDocumentNumber(userDocument)).thenReturn(dbMonthlyBudget);

        MonthlyBudgetEntity result = getMonthlyBudgetGateway.getLastMonthlyBudget(userDocument);

        assertNotNull(result);
        assertEquals(1, result.getIdMonthlyBudget());
        verify(repository, times(1)).findMostRecentMonthlyBudgetByDocumentNumber(userDocument);
    }
}
