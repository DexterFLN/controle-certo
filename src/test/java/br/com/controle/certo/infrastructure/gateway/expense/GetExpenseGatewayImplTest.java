//package br.com.controle.certo.infrastructure.gateway.expense;
//
//import br.com.controle.certo.domain.entities.ExpenseEntity;
//import br.com.controle.certo.infrastructure.repository.impl.DbExpenseRepository;
//import br.com.controle.certo.infrastructure.repository.model.DbExpense;
//import br.com.controle.certo.infrastructure.repository.model.DbCategory;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//import static java.util.Collections.singletonList;
//
//@ExtendWith(MockitoExtension.class)
//public class GetExpenseGatewayImplTest {
//
//    @Mock
//    private DbExpenseRepository repository;
//
//    @InjectMocks
//    private GetExpenseGatewayImpl getExpenseGateway;
//
//    private DbExpense createTestDbExpense() {
//        DbCategory dbCategory = new DbCategory();
//        dbCategory.setIdCategory(1);
//        dbCategory.setCategoryName("Test Category");
//        dbCategory.setCategoryDescription("Test Description");
//
//        DbExpense dbExpense = new DbExpense();
//        dbExpense.setIdExpense(1);
//        dbExpense.setUuidExpense("123e4567-e89b-12d3-a456-426614174000");
//        dbExpense.setExpenseDescription("Test Expense");
//        dbExpense.setExpenseValue(100.0);
//        dbExpense.setDbCategory(dbCategory);
//
//        return dbExpense;
//    }
//
//    @Test
//    public void testGetAllExpenseByUserDocument() {
//        String userDocument = "12345678900";
//        DbExpense dbExpense = createTestDbExpense();
//        List<DbExpense> dbExpenses = singletonList(dbExpense);
//
//        when(repository.getAllExpenseByUserDocument(userDocument)).thenReturn(dbExpenses);
//
//        List<ExpenseEntity> expenses = getExpenseGateway.getAllExpenseByUserDocument(userDocument);
//
//        assertEquals(1, expenses.size());
//        assertEquals("Test Expense", expenses.get(0).getExpenseDescription());
//    }
//
//    @Test
//    public void testGetExpenseCurrentMonth() {
//        String userDocument = "12345678900";
//        int month = 7;
//        int year = 2024;
//        DbExpense dbExpense = createTestDbExpense();
//        List<DbExpense> dbExpenses = singletonList(dbExpense);
//
//        when(repository.getExpenseCurrentMonthByUserDocument(userDocument, month, year)).thenReturn(dbExpenses);
//
//        List<ExpenseEntity> expenses = getExpenseGateway.getExpenseCurrentMonth(userDocument, month, year);
//
//        assertEquals(1, expenses.size());
//        assertEquals("Test Expense", expenses.get(0).getExpenseDescription());
//    }
//
//    @Test
//    public void testGetExpenseById() {
//        Integer idExpense = 1;
//        String userDocument = "12345678900";
//        DbExpense dbExpense = createTestDbExpense();
//
//        when(repository.getExpenseByIdExpenseAndUserDocument(idExpense, userDocument)).thenReturn(dbExpense);
//
//        ExpenseEntity expense = getExpenseGateway.getExpenseById(idExpense, userDocument);
//
//        assertEquals(dbExpense.getIdExpense(), expense.getIdExpense());
//        assertEquals("Test Expense", expense.getExpenseDescription());
//    }
//}
