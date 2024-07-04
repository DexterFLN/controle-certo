package br.com.controle.certo.application.usecase.user;

import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseUser;

import java.util.List;

public interface GetUserAllUseCase {
    List<ResponseUser> getUserAll();
}
