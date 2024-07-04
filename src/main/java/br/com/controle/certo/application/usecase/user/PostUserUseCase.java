package br.com.controle.certo.application.usecase.user;

import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUser;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseUser;

public interface PostUserUseCase {
    ResponseUser postUser(RequestUser body, String document);
}
