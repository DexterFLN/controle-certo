package br.com.controle.certo.application.gateway.user;

import br.com.controle.certo.domain.entities.UserEntity;

import java.util.List;

public interface GetUserGateway {
    UserEntity getUserByUserDocument(String userDocument);
    List<UserEntity> getUserAll();
}
