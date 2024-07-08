package br.com.controle.certo.application.mapper;

import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseUser;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

public class UserUseCaseMapper {

    public static ResponseUser userEntityToResponseUser(UserEntity body) {
        return nonNull(body) ? ResponseUser.builder()
                .idUser(body.getIdUser())
                .userName(body.getUserName())
                .documentNumber(body.getDocumentNumber())
                .emailUser(body.getEmailUser())
                .phoneNumber(body.getPhoneNumber())
                .uf(body.getUf())
                .cep(body.getCep())
                .neighborhood(body.getNeighborhood())
                .county(body.getCounty())
                .acceptTerms(body.getAcceptTerms())
                .lvlUser(body.getLvlUser())
                .xpUser(body.getXpUser())
                .dhCreate(body.getDhCreate())
                .dhUpdate(body.getDhUpdate())
                .build() : null;
    }

    public static List<ResponseUser> userEntityToResponseUser(List<UserEntity> body) {
        return nonNull(body) ? body.stream().map(m ->
                ResponseUser.builder()
                        .idUser(m.getIdUser())
                        .userName(m.getUserName())
                        .documentNumber(m.getDocumentNumber())
                        .emailUser(m.getEmailUser())
                        .phoneNumber(m.getPhoneNumber())
                        .uf(m.getUf())
                        .cep(m.getCep())
                        .neighborhood(m.getNeighborhood())
                        .county(m.getCounty())
                        .acceptTerms(m.getAcceptTerms())
                        .lvlUser(m.getLvlUser())
                        .xpUser(m.getXpUser())
                        .dhCreate(m.getDhCreate())
                        .dhUpdate(m.getDhUpdate())
                        .dhExclude(m.getDhExclude())
                        .build()).collect(Collectors.toList()) : emptyList();
    }
}