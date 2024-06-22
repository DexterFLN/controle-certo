package br.com.controle.certo.infrastructure.mappers;

import br.com.controle.certo.domain.entities.ItemBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestItemBudget;
import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import br.com.controle.certo.infrastructure.repository.model.DbItemBudget;

public class ItemBudgetMapper {
    public static DbItemBudget itemBudgetToDbItemBudget(RequestItemBudget body, DbCategory category) {
        DbItemBudget response = new DbItemBudget();
        response.setBudgetTarget(false);
        response.setCategoryPercentage(body.getCategoryPercentage());
        response.setDbCategory(category);
        return response;
    }

    public static ItemBudgetEntity dbItemBudgetToItemBudgetEntity (DbItemBudget body) {
        return ItemBudgetEntity.builder()
                .idItemBudget(body.getIdItemBudget())
                .budgetTarget(body.getBudgetTarget())
                .categoryPercentage(body.getCategoryPercentage())
                .dhCreate(body.getDhCreate())
                .dhUpdate(body.getDhUpdate())
                .dbCategory(body.getDbCategory())
                .build();
    }
}