package br.com.controle.certo.application.mapper;

import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.ItemBudgetEntity;
import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseItemBudget;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseMonthlyBudget;
import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MonthlyBudgetUseCaseMapperTest {

    @Test
    public void testMonthlyBudgetEntityToResponseMonthlyBudgetEntity() {
        DbCategory categoryEntity = DbCategory.builder()
                .idCategory(1)
                .categoryName("Test Category")
                .categoryDescription("Test Description")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .build();

        ItemBudgetEntity itemBudgetEntity1 = ItemBudgetEntity.builder()
                .idItemBudget(1)
                .budgetTarget(false)
                .expenseValue(200.0)
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .dbCategory(categoryEntity)
                .build();

        ItemBudgetEntity itemBudgetEntity2 = ItemBudgetEntity.builder()
                .idItemBudget(2)
                .budgetTarget(false)
                .expenseValue(300.0)
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .dbCategory(categoryEntity)
                .build();

        List<ItemBudgetEntity> itemBudgetEntities = Arrays.asList(itemBudgetEntity1, itemBudgetEntity2);

        MonthlyBudgetEntity monthlyBudgetEntity = MonthlyBudgetEntity.builder()
                .idMonthlyBudget(1)
                .monthlyIncome(3000.0)
                .monthlyReference("2023-07")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .dhExclude(null)
                .itemBudgetEntityList(itemBudgetEntities)
                .build();

        ResponseMonthlyBudget response = MonthlyBudgetUseCaseMapper.monthlyBudgetEntityToResponseMonthlyBudgetEntity(monthlyBudgetEntity);

        assertNotNull(response);
        assertEquals(monthlyBudgetEntity.getIdMonthlyBudget(), response.getIdMonthlyBudget());
        assertEquals(monthlyBudgetEntity.getDhCreate(), response.getDhCreate());
        assertEquals(monthlyBudgetEntity.getDhUpdate(), response.getDhUpdate());
        assertEquals(monthlyBudgetEntity.getDhExclude(), response.getDhExclude());
        assertEquals(monthlyBudgetEntity.getMonthlyIncome(), response.getMonthlyIncome());
        assertEquals(monthlyBudgetEntity.getMonthlyReference(), response.getMonthlyReference());
        assertNotNull(response.getItemBudgetEntityList());
        assertEquals(2, response.getItemBudgetEntityList().size());

        ResponseItemBudget responseItem1 = response.getItemBudgetEntityList().get(0);
        assertEquals(itemBudgetEntity1.getIdItemBudget(), responseItem1.getIdItemBudget());
        assertEquals(itemBudgetEntity1.getBudgetTarget(), responseItem1.getBudgetTarget());
        assertEquals(itemBudgetEntity1.getExpenseValue(), responseItem1.getExpenseValue());
        assertEquals(itemBudgetEntity1.getDhCreate(), responseItem1.getDhCreate());
        assertEquals(itemBudgetEntity1.getDhUpdate(), responseItem1.getDhUpdate());
        ResponseCategory responseCategory1 = responseItem1.getDbCategory();
        assertEquals(itemBudgetEntity1.getDbCategory().getIdCategory(), responseCategory1.getIdCategory());
        assertEquals(itemBudgetEntity1.getDbCategory().getCategoryName(), responseCategory1.getCategoryName());
        assertEquals(itemBudgetEntity1.getDbCategory().getCategoryDescription(), responseCategory1.getCategoryDescription());
        assertEquals(itemBudgetEntity1.getDbCategory().getDhCreate(), responseCategory1.getDhCreate());

        ResponseItemBudget responseItem2 = response.getItemBudgetEntityList().get(1);
        assertEquals(itemBudgetEntity2.getIdItemBudget(), responseItem2.getIdItemBudget());
        assertEquals(itemBudgetEntity2.getBudgetTarget(), responseItem2.getBudgetTarget());
        assertEquals(itemBudgetEntity2.getExpenseValue(), responseItem2.getExpenseValue());
        assertEquals(itemBudgetEntity2.getDhCreate(), responseItem2.getDhCreate());
        assertEquals(itemBudgetEntity2.getDhUpdate(), responseItem2.getDhUpdate());
        ResponseCategory responseCategory2 = responseItem2.getDbCategory();
        assertEquals(itemBudgetEntity2.getDbCategory().getIdCategory(), responseCategory2.getIdCategory());
        assertEquals(itemBudgetEntity2.getDbCategory().getCategoryName(), responseCategory2.getCategoryName());
        assertEquals(itemBudgetEntity2.getDbCategory().getCategoryDescription(), responseCategory2.getCategoryDescription());
        assertEquals(itemBudgetEntity2.getDbCategory().getDhCreate(), responseCategory2.getDhCreate());
    }

    @Test
    public void testMonthlyBudgetEntityToResponseMonthlyBudgetEntity_NullInput() {
        ResponseMonthlyBudget response = MonthlyBudgetUseCaseMapper.monthlyBudgetEntityToResponseMonthlyBudgetEntity(null);
        assertNull(response);
    }
}
