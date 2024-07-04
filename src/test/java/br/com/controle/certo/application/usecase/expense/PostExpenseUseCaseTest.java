package br.com.controle.certo.application.usecase.expense;
import br.com.controle.certo.application.gateway.category.GetCategoryGateway;
import br.com.controle.certo.application.gateway.expense.PostExpenseGateway;
import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.application.usecase.expense.impl.PostExpenseUseCaseImpl;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestExpense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
public class PostExpenseUseCaseTest {
    @Mock
    private GetUserGateway userGateway;
    @Mock
    private GetCategoryGateway categoryGateway;
    @Mock
    private PostExpenseGateway postExpenseGateway;
    @InjectMocks
    private PostExpenseUseCaseImpl postExpenseUseCase;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testPostExpense() {
        RequestExpense request = RequestExpense.builder()
                .expenseDescription("Teste de despesa")
                .expenseValue(100.0)
                .currentInstallment(1)
                .totalInstallment(10)
                .expenseType("Tipo")
                .dbCategory(1)
                .build();
        String userDocument = "123456789";
        UserEntity userEntity = UserEntity.builder().build();
        CategoryEntity categoryEntity = CategoryEntity.builder().idCategory(1).build();
        when(userGateway.getUserByUserDocument(userDocument)).thenReturn(userEntity);
        when(categoryGateway.getCategoryById(userDocument, request.getDbCategory())).thenReturn(categoryEntity);
        postExpenseUseCase.postExpense(request, userDocument);
        verify(postExpenseGateway, times(1)).saveExpense(anyList());
    }
}
