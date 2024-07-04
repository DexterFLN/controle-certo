package br.com.controle.certo.application.usecase.expense;

import br.com.controle.certo.application.gateway.expense.GetExpenseGateway;
import br.com.controle.certo.application.usecase.expense.impl.GetExpenseUseCaseImpl;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseExpensive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetExpenseUseCaseTest {

    @Mock
    private GetExpenseGateway expenseGateway;

    @InjectMocks
    private GetExpenseUseCaseImpl getExpenseUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllExpenseByDocument() {
        String userDocument = "123456789";

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .idCategory(1)
                .categoryName("Category Name")
                .build();

        ExpenseEntity expenseEntity = ExpenseEntity.builder()
                .idExpense(1)
                .uuidExpense("uuid-123")
                .expenseDescription("description")
                .expenseValue(100.0)
                .currentInstallment(1)
                .totalInstallment(10)
                .expenseType("type")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .categoryEntity(categoryEntity)
                .userEntity(null)
                .build();

        List<ExpenseEntity> expenseEntities = List.of(expenseEntity);

        ResponseExpensive expectedExpense = ResponseExpensive.builder()
                .idExpense(1)
                .uuidDespesa("uuid-123")
                .expenseDescription("description")
                .expenseValue(100.0)
                .currentInstallment(1)
                .totalInstallment(10)
                .expenseType("type")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .responseCategory(null)
                .build();

        List<ResponseExpensive> expectedExpenses = List.of(expectedExpense);

        when(expenseGateway.getAllExpenseByUserDocument(userDocument)).thenReturn(expenseEntities);

        List<ResponseExpensive> actualExpenses = getExpenseUseCase.getAllExpenseByDocument(userDocument);

        assertEquals(expectedExpenses.size(), actualExpenses.size());
        assertEquals(expectedExpenses.get(0).getIdExpense(), actualExpenses.get(0).getIdExpense());
        assertEquals(expectedExpenses.get(0).getUuidDespesa(), actualExpenses.get(0).getUuidDespesa());
        // Adicione mais asserções conforme necessário para outros campos
    }
}
