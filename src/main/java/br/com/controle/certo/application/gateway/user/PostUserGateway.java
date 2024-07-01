package br.com.controle.certo.application.gateway.user;

import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUser;

public interface PostUserGateway {
    UserEntity postUser(RequestUser body, String document);
}
