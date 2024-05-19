package br.com.controle.certo.application.usecase.user;

import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUser;

public interface PostUserUseCase {
    void postUser(RequestUser body, String document);
}
