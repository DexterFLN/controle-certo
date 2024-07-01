package br.com.controle.certo.application.usecase.user;

import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestPutUser;

public interface PutUserUseCase {
    void putUserByUserDocument(RequestPutUser body, String userDocument);
}
