package br.com.controle.certo.application.gateway.user;

import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUser;

public interface PostUserGateway {
    void postUser(RequestUser body, String document);
}
