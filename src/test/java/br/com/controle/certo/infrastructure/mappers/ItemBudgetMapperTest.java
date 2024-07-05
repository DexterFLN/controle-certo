package br.com.controle.certo.infrastructure.mappers;

import br.com.controle.certo.domain.entities.ItemBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestItemBudget;
import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import br.com.controle.certo.infrastructure.repository.model.DbItemBudget;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItemBudgetMapperTest {

    @Test
    public void testItemBudgetToDbItemBudget() {
        RequestItemBudget requestItemBudget = RequestItemBudget.builder()
                .expenseValue(150.0)
                .dbCategory(1)
                .build();

        DbCategory dbCategory = new DbCategory();
        dbCategory.setIdCategory(1);
        dbCategory.setCategoryName("Test Category");
        dbCategory.setCategoryDescription("Test Description");

        DbItemBudget dbItemBudget = ItemBudgetMapper.itemBudgetToDbItemBudget(requestItemBudget, dbCategory);

        assertNotNull(dbItemBudget);
        assertEquals(false, dbItemBudget.getBudgetTarget());
        assertEquals(150.0, dbItemBudget.getExpenseValue());
        assertNotNull(dbItemBudget.getDbCategory());
        assertEquals(1, dbItemBudget.getDbCategory().getIdCategory());
        assertEquals("Test Category", dbItemBudget.getDbCategory().getCategoryName());
        assertEquals("Test Description", dbItemBudget.getDbCategory().getCategoryDescription());
    }

    @Test
    public void testDbItemBudgetToItemBudgetEntity() {
        DbCategory dbCategory = new DbCategory();
        dbCategory.setIdCategory(1);
        dbCategory.setCategoryName("Test Category");
        dbCategory.setCategoryDescription("Test Description");

        DbItemBudget dbItemBudget = new DbItemBudget();
        dbItemBudget.setIdItemBudget(1);
        dbItemBudget.setBudgetTarget(false);
        dbItemBudget.setExpenseValue(150.0);
        dbItemBudget.setDhCreate(LocalDateTime.now());
        dbItemBudget.setDhUpdate(LocalDateTime.now());
        dbItemBudget.setDbCategory(dbCategory);

        ItemBudgetEntity itemBudgetEntity = ItemBudgetMapper.dbItemBudgetToItemBudgetEntity(dbItemBudget);

        assertNotNull(itemBudgetEntity);
        assertEquals(1, itemBudgetEntity.getIdItemBudget());
        assertEquals(false, itemBudgetEntity.getBudgetTarget());
        assertEquals(150.0, itemBudgetEntity.getExpenseValue());
        assertEquals(dbItemBudget.getDhCreate(), itemBudgetEntity.getDhCreate());
        assertEquals(dbItemBudget.getDhUpdate(), itemBudgetEntity.getDhUpdate());
        assertNotNull(itemBudgetEntity.getDbCategory());
        assertEquals(1, itemBudgetEntity.getDbCategory().getIdCategory());
        assertEquals("Test Category", itemBudgetEntity.getDbCategory().getCategoryName());
        assertEquals("Test Description", itemBudgetEntity.getDbCategory().getCategoryDescription());
    }
}
