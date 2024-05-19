package br.com.controle.certo.infrastructure.mappers;

import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.repository.model.DbUser;

public class UserMapper {

    public static UserEntity dbUserToUserEntity(DbUser body) {
        return UserEntity.builder()
                .idUser(body.getIdUser())
                .documentNumber(body.getDocumentNumber())
                .emailUser(body.getEmailUser())
                .phoneNumber(body.getPhoneNumber())
                .uf(body.getUf())
                .county(body.getCounty())
                .acceptTerms(body.getAcceptTerms())
                .lvlUser(body.getLvlUser())
                .xpUser(body.getXpUser())
                .dhCreate(body.getDhCreate())
                .dhUpdate(body.getDhUpdate())
                .dbExpenseList(body.getDbExpenseList())
                .dbMonthlyBudgetList(body.getDbMonthlyBudgetList())
                .build();
    }

    public static DbUser userEntityToDbUser(UserEntity body) {
        DbUser dbUser = new DbUser();
        dbUser.setIdUser(body.getIdUser());
        dbUser.setDocumentNumber(body.getDocumentNumber());
        dbUser.setEmailUser(body.getEmailUser());
        dbUser.setPhoneNumber(body.getPhoneNumber());
        dbUser.setUf(body.getUf());
        dbUser.setCounty(body.getCounty());
        dbUser.setAcceptTerms(body.getAcceptTerms());
        dbUser.setLvlUser(body.getLvlUser());
        dbUser.setXpUser(body.getXpUser());
        dbUser.setDhCreate(body.getDhCreate());
        dbUser.setDhUpdate(body.getDhUpdate());
        dbUser.setDbExpenseList(body.getDbExpenseList());
        dbUser.setDbMonthlyBudgetList(body.getDbMonthlyBudgetList());
        return dbUser;
    }
}
