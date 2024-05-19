package br.com.controle.certo.application.gateway;

import br.com.controle.certo.domain.entities.UserEntity;

public interface GetUserGateway {
    UserEntity getUser(String document);
}
