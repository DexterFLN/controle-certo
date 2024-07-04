package br.com.controle.certo.application.usecase.user;

import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseUser;

public interface GetUserByUserDocumentUseCase {
    ResponseUser getUserByUserDocument(String userDocument);
}
