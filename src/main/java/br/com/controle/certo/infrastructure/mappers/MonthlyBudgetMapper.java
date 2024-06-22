package br.com.controle.certo.infrastructure.mappers;

import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestMonthlyBudget;
import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import br.com.controle.certo.infrastructure.repository.model.DbItemBudget;
import br.com.controle.certo.infrastructure.repository.model.DbMonthlyBudget;
import br.com.controle.certo.infrastructure.repository.model.DbUser;

import java.util.List;
import java.util.Objects;

import static br.com.controle.certo.infrastructure.mappers.ItemBudgetMapper.itemBudgetToDbItemBudget;
import static java.util.Objects.nonNull;

public class MonthlyBudgetMapper {
    public static DbMonthlyBudget monthlyBudgetEntityToDbMonthlyBudget(RequestMonthlyBudget body, DbUser user) {
        DbMonthlyBudget response = new DbMonthlyBudget();
        response.setMonthlyReference(body.getMonthlyReference());
        response.setMonthlyIncome(body.getMonthlyIncome());
        response.setDbUser(user);
        return response;
    }

    public static DbMonthlyBudget monthl2yBudgetEntityToDbMonthlyBudget(RequestMonthlyBudget body, List<DbCategory> dbCategoryList, DbUser user) {
        List<DbItemBudget> dbItemBudgetList = body.getItemBudgetList().stream().map(m -> {
            if (!dbCategoryList.isEmpty()) {
                DbCategory dbCategory = dbCategoryList.stream().filter(f -> f.getIdCategory().equals(m.getDbCategory())).findFirst().orElseGet(null);
                return itemBudgetToDbItemBudget(m, dbCategory);
            }
            return null;
        }).toList();

        DbMonthlyBudget response = new DbMonthlyBudget();
        response.setMonthlyReference(body.getMonthlyReference());
        response.setMonthlyIncome(body.getMonthlyIncome());
        response.setDbUser(user);
        response.setDbItemBudget(dbItemBudgetList);
        return response;
    }

    public static MonthlyBudgetEntity dbMonthlyBudgetToMonthlyBudgetEntity(DbMonthlyBudget body) {
        return nonNull(body) ? MonthlyBudgetEntity.builder()
                .idMonthlyBudget(body.getIdMonthlyBudget())
                .dhCreate(body.getDhCreate())
                .dhUpdate(body.getDhUpdate())
                .dhExclude(body.getDhExclude())
                .monthlyIncome(body.getMonthlyIncome())
                .monthlyReference(body.getMonthlyReference())
                .itemBudgetEntityList(body.getDbItemBudget().stream().map(ItemBudgetMapper::dbItemBudgetToItemBudgetEntity).toList())
                .build() : null;
    }
}