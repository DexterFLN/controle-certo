package br.com.controle.certo.application.gateway.user;

import br.com.controle.certo.domain.entities.UserEntity;

public interface PutUserGateway {
    void putUserByUserDocument(UserEntity body);
}
