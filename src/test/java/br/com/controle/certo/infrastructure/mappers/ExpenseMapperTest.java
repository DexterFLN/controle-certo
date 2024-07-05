package br.com.controle.certo.infrastructure.mappers;

import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.infrastructure.repository.model.DbExpense;
import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExpenseMapperTest {

    private DbExpense createTestDbExpense() {
        DbCategory dbCategory = new DbCategory();
        dbCategory.setIdCategory(1);
        dbCategory.setCategoryName("Test Category");
        dbCategory.setCategoryDescription("Test Description");
        dbCategory.setDhCreate(LocalDateTime.now());
        dbCategory.setDhUpdate(LocalDateTime.now());

        DbExpense dbExpense = new DbExpense();
        dbExpense.setIdExpense(1);
        dbExpense.setUuidExpense("123e4567-e89b-12d3-a456-426614174000");
        dbExpense.setExpenseDescription("Test Expense");
        dbExpense.setExpenseValue(100.0);
        dbExpense.setCurrentInstallment(1);
        dbExpense.setTotalInstallment(12);
        dbExpense.setExpenseType("Type");
        dbExpense.setDhCreate(LocalDateTime.now());
        dbExpense.setDhUpdate(LocalDateTime.now());
        dbExpense.setDbCategory(dbCategory);

        return dbExpense;
    }

    @Test
    public void testDbExpenseToExpenseEntity() {
        DbExpense dbExpense = createTestDbExpense();

        ExpenseEntity expenseEntity = ExpenseMapper.dbExpenseToExpenseEntity(dbExpense);

        assertNotNull(expenseEntity);
        assertEquals(dbExpense.getIdExpense(), expenseEntity.getIdExpense());
        assertEquals(dbExpense.getUuidExpense(), expenseEntity.getUuidExpense());
        assertEquals(dbExpense.getExpenseDescription(), expenseEntity.getExpenseDescription());
        assertEquals(dbExpense.getExpenseValue(), expenseEntity.getExpenseValue());
        assertEquals(dbExpense.getCurrentInstallment(), expenseEntity.getCurrentInstallment());
        assertEquals(dbExpense.getTotalInstallment(), expenseEntity.getTotalInstallment());
        assertEquals(dbExpense.getExpenseType(), expenseEntity.getExpenseType());
        assertEquals(dbExpense.getDhCreate(), expenseEntity.getDhCreate());
        assertEquals(dbExpense.getDhUpdate(), expenseEntity.getDhUpdate());
        assertNotNull(expenseEntity.getCategoryEntity());
        assertEquals(dbExpense.getDbCategory().getIdCategory(), expenseEntity.getCategoryEntity().getIdCategory());
        assertEquals(dbExpense.getDbCategory().getCategoryName(), expenseEntity.getCategoryEntity().getCategoryName());
        assertEquals(dbExpense.getDbCategory().getCategoryDescription(), expenseEntity.getCategoryEntity().getCategoryDescription());
        assertEquals(dbExpense.getDbCategory().getDhCreate(), expenseEntity.getCategoryEntity().getDhCreate());
        assertEquals(dbExpense.getDbCategory().getDhUpdate(), expenseEntity.getCategoryEntity().getDhUpdate());
    }

    @Test
    public void testListDbExpenseToListExpenseEntity() {
        DbExpense dbExpense1 = createTestDbExpense();
        DbExpense dbExpense2 = createTestDbExpense();
        dbExpense2.setIdExpense(2);
        dbExpense2.setUuidExpense("223e4567-e89b-12d3-a456-426614174000");
        dbExpense2.setExpenseDescription("Another Test Expense");

        List<DbExpense> dbExpenseList = Arrays.asList(dbExpense1, dbExpense2);

        List<ExpenseEntity> expenseEntityList = ExpenseMapper.listDbExpenseToListExpenseEntity(dbExpenseList);

        assertNotNull(expenseEntityList);
        assertEquals(2, expenseEntityList.size());

        ExpenseEntity expenseEntity1 = expenseEntityList.get(0);
        ExpenseEntity expenseEntity2 = expenseEntityList.get(1);

        assertEquals(dbExpense1.getIdExpense(), expenseEntity1.getIdExpense());
        assertEquals(dbExpense1.getUuidExpense(), expenseEntity1.getUuidExpense());
        assertEquals(dbExpense1.getExpenseDescription(), expenseEntity1.getExpenseDescription());
        assertEquals(dbExpense1.getExpenseValue(), expenseEntity1.getExpenseValue());
        assertEquals(dbExpense1.getCurrentInstallment(), expenseEntity1.getCurrentInstallment());
        assertEquals(dbExpense1.getTotalInstallment(), expenseEntity1.getTotalInstallment());
        assertEquals(dbExpense1.getExpenseType(), expenseEntity1.getExpenseType());
        assertEquals(dbExpense1.getDhCreate(), expenseEntity1.getDhCreate());
        assertEquals(dbExpense1.getDhUpdate(), expenseEntity1.getDhUpdate());
        assertNotNull(expenseEntity1.getCategoryEntity());
        assertEquals(dbExpense1.getDbCategory().getIdCategory(), expenseEntity1.getCategoryEntity().getIdCategory());
        assertEquals(dbExpense1.getDbCategory().getCategoryName(), expenseEntity1.getCategoryEntity().getCategoryName());
        assertEquals(dbExpense1.getDbCategory().getCategoryDescription(), expenseEntity1.getCategoryEntity().getCategoryDescription());
        assertEquals(dbExpense1.getDbCategory().getDhCreate(), expenseEntity1.getCategoryEntity().getDhCreate());
        assertEquals(dbExpense1.getDbCategory().getDhUpdate(), expenseEntity1.getCategoryEntity().getDhUpdate());

        assertEquals(dbExpense2.getIdExpense(), expenseEntity2.getIdExpense());
        assertEquals(dbExpense2.getUuidExpense(), expenseEntity2.getUuidExpense());
        assertEquals(dbExpense2.getExpenseDescription(), expenseEntity2.getExpenseDescription());
        assertEquals(dbExpense2.getExpenseValue(), expenseEntity2.getExpenseValue());
        assertEquals(dbExpense2.getCurrentInstallment(), expenseEntity2.getCurrentInstallment());
        assertEquals(dbExpense2.getTotalInstallment(), expenseEntity2.getTotalInstallment());
        assertEquals(dbExpense2.getExpenseType(), expenseEntity2.getExpenseType());
        assertEquals(dbExpense2.getDhCreate(), expenseEntity2.getDhCreate());
        assertEquals(dbExpense2.getDhUpdate(), expenseEntity2.getDhUpdate());
        assertNotNull(expenseEntity2.getCategoryEntity());
        assertEquals(dbExpense2.getDbCategory().getIdCategory(), expenseEntity2.getCategoryEntity().getIdCategory());
        assertEquals(dbExpense2.getDbCategory().getCategoryName(), expenseEntity2.getCategoryEntity().getCategoryName());
        assertEquals(dbExpense2.getDbCategory().getCategoryDescription(), expenseEntity2.getCategoryEntity().getCategoryDescription());
        assertEquals(dbExpense2.getDbCategory().getDhCreate(), expenseEntity2.getCategoryEntity().getDhCreate());
        assertEquals(dbExpense2.getDbCategory().getDhUpdate(), expenseEntity2.getCategoryEntity().getDhUpdate());
    }
}
