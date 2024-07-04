package br.com.controle.certo.application.usecase.expense;

import br.com.controle.certo.application.gateway.expense.GetExpenseGateway;
import br.com.controle.certo.application.usecase.expense.impl.GetExpenseCurrentMonthUseCaseImpl;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseExpensive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.controle.certo.application.mapper.ExpenseUseCaseMapper.listExpenseEntityToListResponseExpense;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetExpenseCurrentMonthUseCaseTest {

    @Mock
    private GetExpenseGateway expenseGateway;

    @InjectMocks
    private GetExpenseCurrentMonthUseCaseImpl getExpenseCurrentMonthUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetExpenseCurrentMonth() {
        String userDocument = "123456789";
        int month = 7;
        int year = 2024;

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .idCategory(1)
                .categoryName("Food")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .idUser(1)
                .userName(userDocument)
                .build();

        ExpenseEntity expenseEntity = ExpenseEntity.builder()
                .idExpense(1)
                .uuidExpense("uuid")
                .expenseDescription("description")
                .expenseValue(100.0)
                .currentInstallment(1)
                .totalInstallment(1)
                .expenseType("type")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .categoryEntity(categoryEntity)
                .userEntity(userEntity)
                .build();

        List<ExpenseEntity> expenseEntities = List.of(expenseEntity);
        List<ResponseExpensive> expectedExpenses = listExpenseEntityToListResponseExpense(expenseEntities);

        when(expenseGateway.getExpenseCurrentMonth(userDocument, month, year)).thenReturn(expenseEntities);

        List<ResponseExpensive> actualExpenses = getExpenseCurrentMonthUseCase.getExpenseCurrentMonth(userDocument, month, year);

        // Verificar campo a campo em vez de verificar referências
        for (int i = 0; i < expectedExpenses.size(); i++) {
            ResponseExpensive expected = expectedExpenses.get(i);
            ResponseExpensive actual = actualExpenses.get(i);
            assertEquals(expected.getIdExpense(), actual.getIdExpense());
            assertEquals(expected.getUuidDespesa(), actual.getUuidDespesa());
            assertEquals(expected.getExpenseDescription(), actual.getExpenseDescription());
            assertEquals(expected.getExpenseValue(), actual.getExpenseValue());
            assertEquals(expected.getCurrentInstallment(), actual.getCurrentInstallment());
            assertEquals(expected.getTotalInstallment(), actual.getTotalInstallment());
            assertEquals(expected.getExpenseType(), actual.getExpenseType());
            assertEquals(expected.getDhCreate(), actual.getDhCreate());
            assertEquals(expected.getDhUpdate(), actual.getDhUpdate());
            assertEquals(expected.getResponseCategory().getIdCategory(), actual.getResponseCategory().getIdCategory());
            assertEquals(expected.getResponseCategory().getCategoryName(), actual.getResponseCategory().getCategoryName());
        }
    }
}
