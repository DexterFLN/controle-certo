package br.com.controle.certo.application.usecase.expense;
import br.com.controle.certo.application.gateway.category.GetCategoryGateway;
import br.com.controle.certo.application.gateway.expense.GetExpenseGateway;
import br.com.controle.certo.application.gateway.expense.PutExpenseGateway;
import br.com.controle.certo.application.usecase.expense.impl.PutExpenseUseCaseImpl;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestPutExpense;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import static org.mockito.Mockito.*;
public class PutExpenseUseCaseTest {
    @Mock
    private GetExpenseGateway getExpenseGateway;
    @Mock
    private PutExpenseGateway putExpenseGateway;
    @Mock
    private GetCategoryGateway getCategoryGateway;
    @InjectMocks
    private PutExpenseUseCaseImpl putExpenseUseCase;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testUpdateExpenseById() {
        Integer idExpense = 1;
        String userDocument = "123456789";
        boolean recursive = false;
        ResponseCategory responseCategory = ResponseCategory.builder()
                .idCategory(1)
                .categoryName("Category")
                .categoryDescription("Description")
                .build();
        RequestPutExpense request = RequestPutExpense.builder()
                .idDespesa(idExpense)
                .uuidDespesa("some-uuid")
                .expenseDescription("Updated Description")
                .expenseValue(200.0)
                .responseCategory(responseCategory)
                .build();
        ExpenseEntity expenseEntity = ExpenseEntity.builder()
                .idExpense(idExpense)
                .expenseDescription("Test Description")
                .expenseValue(100.0)
                .currentInstallment(1)
                .totalInstallment(10)
                .expenseType("Test Type")
                .dhCreate(LocalDateTime.now().minusDays(1))
                .build();
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .idCategory(1)
                .categoryName("Category")
                .categoryDescription("Description")
                .dhCreate(LocalDateTime.now().minusDays(2))
                .dhUpdate(LocalDateTime.now().minusDays(1))
                .build();
        when(getExpenseGateway.getExpenseById(idExpense, userDocument)).thenReturn(expenseEntity);
        when(getCategoryGateway.getCategoryById(userDocument, responseCategory.getIdCategory())).thenReturn(categoryEntity);
        putExpenseUseCase.updateExpenseById(idExpense, userDocument, recursive, request);
        verify(putExpenseGateway).updateExpenseById(any(ExpenseEntity.class));
    }
}
