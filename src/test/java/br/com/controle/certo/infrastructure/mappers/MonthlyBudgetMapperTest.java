package br.com.controle.certo.infrastructure.mappers;

import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestItemBudget;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestMonthlyBudget;
import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import br.com.controle.certo.infrastructure.repository.model.DbItemBudget;
import br.com.controle.certo.infrastructure.repository.model.DbMonthlyBudget;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MonthlyBudgetMapperTest {

    @Test
    public void testMonthlyBudgetEntityToDbMonthlyBudget() {
        RequestMonthlyBudget requestMonthlyBudget = RequestMonthlyBudget.builder()
                .monthlyReference("2024-07")
                .monthlyIncome(5000.0)
                .build();

        DbUser dbUser = new DbUser();
        dbUser.setIdUser(1);
        dbUser.setUserName("Test User");

        DbMonthlyBudget dbMonthlyBudget = MonthlyBudgetMapper.monthlyBudgetEntityToDbMonthlyBudget(requestMonthlyBudget, dbUser);

        assertNotNull(dbMonthlyBudget);
        assertEquals("2024-07", dbMonthlyBudget.getMonthlyReference());
        assertEquals(5000.0, dbMonthlyBudget.getMonthlyIncome());
        assertNotNull(dbMonthlyBudget.getDbUser());
        assertEquals(1, dbMonthlyBudget.getDbUser().getIdUser());
        assertEquals("Test User", dbMonthlyBudget.getDbUser().getUserName());
    }

    @Test
    public void testMonthl2yBudgetEntityToDbMonthlyBudget() {
        RequestItemBudget requestItemBudget = RequestItemBudget.builder()
                .expenseValue(150.0)
                .dbCategory(1)
                .build();

        RequestMonthlyBudget requestMonthlyBudget = RequestMonthlyBudget.builder()
                .monthlyReference("2024-07")
                .monthlyIncome(5000.0)
                .itemBudgetList(List.of(requestItemBudget))
                .build();

        DbCategory dbCategory = new DbCategory();
        dbCategory.setIdCategory(1);
        dbCategory.setCategoryName("Test Category");
        dbCategory.setCategoryDescription("Test Description");

        DbUser dbUser = new DbUser();
        dbUser.setIdUser(1);
        dbUser.setUserName("Test User");

        DbMonthlyBudget dbMonthlyBudget = MonthlyBudgetMapper.monthl2yBudgetEntityToDbMonthlyBudget(requestMonthlyBudget, List.of(dbCategory), dbUser);

        assertNotNull(dbMonthlyBudget);
        assertEquals("2024-07", dbMonthlyBudget.getMonthlyReference());
        assertEquals(5000.0, dbMonthlyBudget.getMonthlyIncome());
        assertNotNull(dbMonthlyBudget.getDbUser());
        assertEquals(1, dbMonthlyBudget.getDbUser().getIdUser());
        assertEquals("Test User", dbMonthlyBudget.getDbUser().getUserName());
        assertNotNull(dbMonthlyBudget.getDbItemBudget());
        assertEquals(1, dbMonthlyBudget.getDbItemBudget().size());
        DbItemBudget dbItemBudget = dbMonthlyBudget.getDbItemBudget().get(0);
        assertEquals(150.0, dbItemBudget.getExpenseValue());
        assertNotNull(dbItemBudget.getDbCategory());
        assertEquals(1, dbItemBudget.getDbCategory().getIdCategory());
        assertEquals("Test Category", dbItemBudget.getDbCategory().getCategoryName());
        assertEquals("Test Description", dbItemBudget.getDbCategory().getCategoryDescription());
    }

    @Test
    public void testDbMonthlyBudgetToMonthlyBudgetEntity() {
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

        DbMonthlyBudget dbMonthlyBudget = new DbMonthlyBudget();
        dbMonthlyBudget.setIdMonthlyBudget(1);
        dbMonthlyBudget.setMonthlyReference("2024-07");
        dbMonthlyBudget.setMonthlyIncome(5000.0);
        dbMonthlyBudget.setDhCreate(LocalDateTime.now());
        dbMonthlyBudget.setDhUpdate(LocalDateTime.now());
        dbMonthlyBudget.setDbItemBudget(List.of(dbItemBudget));

        MonthlyBudgetEntity monthlyBudgetEntity = MonthlyBudgetMapper.dbMonthlyBudgetToMonthlyBudgetEntity(dbMonthlyBudget);

        assertNotNull(monthlyBudgetEntity);
        assertEquals(1, monthlyBudgetEntity.getIdMonthlyBudget());
        assertEquals("2024-07", monthlyBudgetEntity.getMonthlyReference());
        assertEquals(5000.0, monthlyBudgetEntity.getMonthlyIncome());
        assertEquals(dbMonthlyBudget.getDhCreate(), monthlyBudgetEntity.getDhCreate());
        assertEquals(dbMonthlyBudget.getDhUpdate(), monthlyBudgetEntity.getDhUpdate());
        assertNotNull(monthlyBudgetEntity.getItemBudgetEntityList());
        assertEquals(1, monthlyBudgetEntity.getItemBudgetEntityList().size());
        assertEquals(dbItemBudget.getIdItemBudget(), monthlyBudgetEntity.getItemBudgetEntityList().get(0).getIdItemBudget());
        assertEquals(dbItemBudget.getExpenseValue(), monthlyBudgetEntity.getItemBudgetEntityList().get(0).getExpenseValue());
        assertNotNull(monthlyBudgetEntity.getItemBudgetEntityList().get(0).getDbCategory());
        assertEquals(1, monthlyBudgetEntity.getItemBudgetEntityList().get(0).getDbCategory().getIdCategory());
        assertEquals("Test Category", monthlyBudgetEntity.getItemBudgetEntityList().get(0).getDbCategory().getCategoryName());
        assertEquals("Test Description", monthlyBudgetEntity.getItemBudgetEntityList().get(0).getDbCategory().getCategoryDescription());
    }
}
