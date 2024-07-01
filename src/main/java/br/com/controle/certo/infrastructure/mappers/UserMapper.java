package br.com.controle.certo.infrastructure.mappers;

import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUser;
import br.com.controle.certo.infrastructure.repository.model.DbUser;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

public class UserMapper {

    public static UserEntity dbUserToUserEntity(DbUser body) {
        return nonNull(body) ? UserEntity.builder()
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
                .build() : null;
    }

    public static List<UserEntity> dbUserToUserEntity(List<DbUser> body) {
        return nonNull(body) ? body.stream().map(m ->
                UserEntity.builder()
                        .idUser(m.getIdUser())
                        .documentNumber(m.getDocumentNumber())
                        .emailUser(m.getEmailUser())
                        .phoneNumber(m.getPhoneNumber())
                        .uf(m.getUf())
                        .county(m.getCounty())
                        .acceptTerms(m.getAcceptTerms())
                        .lvlUser(m.getLvlUser())
                        .xpUser(m.getXpUser())
                        .dhCreate(m.getDhCreate())
                        .dhUpdate(m.getDhUpdate())
                        .dbExpenseList(m.getDbExpenseList())
                        .dbMonthlyBudgetList(m.getDbMonthlyBudgetList())
                        .build()).collect(Collectors.toList()) : emptyList();
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

    public static DbUser requestUserToDbUser(RequestUser body, String userDocument) {
        return nonNull(body) ? DbUser.builder()
                .documentNumber(userDocument)
                .emailUser(body.getEmailUser())
                .phoneNumber(body.getPhoneNumber())
                .uf(body.getUf())
                .county(body.getCounty())
                .acceptTerms(body.getAcceptTerms())
                .build() : null;
    }
}