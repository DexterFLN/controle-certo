package br.com.controle.certo.application.mapper;

import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestPutExpense;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseExpensive;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExpenseUseCaseMapperTest {

    @Test
    public void testListExpenseEntityToListResponseExpense() {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .idCategory(1)
                .categoryName("Test Category")
                .categoryDescription("Test Description")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .build();

        ExpenseEntity expenseEntity1 = ExpenseEntity.builder()
                .idExpense(1)
                .uuidExpense("uuid1")
                .expenseDescription("Expense 1")
                .expenseValue(100.0)
                .currentInstallment(1)
                .totalInstallment(1)
                .expenseType("Type1")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .categoryEntity(categoryEntity)
                .build();

        ExpenseEntity expenseEntity2 = ExpenseEntity.builder()
                .idExpense(2)
                .uuidExpense("uuid2")
                .expenseDescription("Expense 2")
                .expenseValue(200.0)
                .currentInstallment(2)
                .totalInstallment(2)
                .expenseType("Type2")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .categoryEntity(categoryEntity)
                .build();

        List<ExpenseEntity> expenseEntities = Arrays.asList(expenseEntity1, expenseEntity2);

        List<ResponseExpensive> responseExpensives = ExpenseUseCaseMapper.listExpenseEntityToListResponseExpense(expenseEntities);

        assertNotNull(responseExpensives);
        assertEquals(2, responseExpensives.size());

        ResponseExpensive responseExpensive1 = responseExpensives.get(0);
        assertEquals(expenseEntity1.getIdExpense(), responseExpensive1.getIdExpense());
        assertEquals(expenseEntity1.getUuidExpense(), responseExpensive1.getUuidDespesa());
        assertEquals(expenseEntity1.getExpenseDescription(), responseExpensive1.getExpenseDescription());
        assertEquals(expenseEntity1.getExpenseValue(), responseExpensive1.getExpenseValue());
        assertEquals(expenseEntity1.getCurrentInstallment(), responseExpensive1.getCurrentInstallment());
        assertEquals(expenseEntity1.getTotalInstallment(), responseExpensive1.getTotalInstallment());
        assertEquals(expenseEntity1.getExpenseType(), responseExpensive1.getExpenseType());
        assertEquals(expenseEntity1.getDhCreate(), responseExpensive1.getDhCreate());
        assertEquals(expenseEntity1.getDhUpdate(), responseExpensive1.getDhUpdate());
        assertEquals(expenseEntity1.getCategoryEntity().getIdCategory(), responseExpensive1.getResponseCategory().getIdCategory());
        assertEquals(expenseEntity1.getCategoryEntity().getCategoryName(), responseExpensive1.getResponseCategory().getCategoryName());
        assertEquals(expenseEntity1.getCategoryEntity().getCategoryDescription(), responseExpensive1.getResponseCategory().getCategoryDescription());
        assertEquals(expenseEntity1.getCategoryEntity().getDhCreate(), responseExpensive1.getResponseCategory().getDhCreate());
        assertEquals(expenseEntity1.getCategoryEntity().getDhUpdate(), responseExpensive1.getResponseCategory().getDhUpdate());

        ResponseExpensive responseExpensive2 = responseExpensives.get(1);
        assertEquals(expenseEntity2.getIdExpense(), responseExpensive2.getIdExpense());
        assertEquals(expenseEntity2.getUuidExpense(), responseExpensive2.getUuidDespesa());
        assertEquals(expenseEntity2.getExpenseDescription(), responseExpensive2.getExpenseDescription());
        assertEquals(expenseEntity2.getExpenseValue(), responseExpensive2.getExpenseValue());
        assertEquals(expenseEntity2.getCurrentInstallment(), responseExpensive2.getCurrentInstallment());
        assertEquals(expenseEntity2.getTotalInstallment(), responseExpensive2.getTotalInstallment());
        assertEquals(expenseEntity2.getExpenseType(), responseExpensive2.getExpenseType());
        assertEquals(expenseEntity2.getDhCreate(), responseExpensive2.getDhCreate());
        assertEquals(expenseEntity2.getDhUpdate(), responseExpensive2.getDhUpdate());
        assertEquals(expenseEntity2.getCategoryEntity().getIdCategory(), responseExpensive2.getResponseCategory().getIdCategory());
        assertEquals(expenseEntity2.getCategoryEntity().getCategoryName(), responseExpensive2.getResponseCategory().getCategoryName());
        assertEquals(expenseEntity2.getCategoryEntity().getCategoryDescription(), responseExpensive2.getResponseCategory().getCategoryDescription());
        assertEquals(expenseEntity2.getCategoryEntity().getDhCreate(), responseExpensive2.getResponseCategory().getDhCreate());
        assertEquals(expenseEntity2.getCategoryEntity().getDhUpdate(), responseExpensive2.getResponseCategory().getDhUpdate());
    }

    @Test
    public void testRequestPutExpenseToExpenseEntity() {
        RequestPutExpense requestPutExpense = RequestPutExpense.builder()
                .idDespesa(1)
                .uuidDespesa("uuid1")
                .expenseDescription("Updated Expense")
                .expenseValue(150.0)
                .responseCategory(ResponseCategory.builder().idCategory(2).build())
                .build();

        ExpenseEntity existingExpense = ExpenseEntity.builder()
                .idExpense(1)
                .uuidExpense("uuid1")
                .expenseDescription("Old Expense")
                .expenseValue(100.0)
                .dhCreate(LocalDateTime.now())
                .categoryEntity(CategoryEntity.builder().idCategory(1).build())
                .build();

        ExpenseEntity updatedExpense = ExpenseUseCaseMapper.requestPutExpenseToExpenseEntity(requestPutExpense, existingExpense);

        assertNotNull(updatedExpense);
        assertEquals(requestPutExpense.getIdDespesa(), updatedExpense.getIdExpense());
        assertEquals(requestPutExpense.getUuidDespesa(), updatedExpense.getUuidExpense());
        assertEquals(requestPutExpense.getExpenseDescription(), updatedExpense.getExpenseDescription());
        assertEquals(requestPutExpense.getExpenseValue(), updatedExpense.getExpenseValue());
        assertEquals(existingExpense.getDhCreate(), updatedExpense.getDhCreate());
        assertEquals(requestPutExpense.getResponseCategory().getIdCategory(), updatedExpense.getCategoryEntity().getIdCategory());
    }
}
