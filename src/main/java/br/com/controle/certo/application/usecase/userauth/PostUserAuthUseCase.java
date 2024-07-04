package br.com.controle.certo.application.usecase.userauth;

import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUserRegistration;
import br.com.controle.certo.infrastructure.repository.auth.DbUserAuth;

public interface PostUserAuthUseCase {
    void postUserAuth(RequestUserRegistration body);
    void changePasswordUserAuth(DbUserAuth userAuth);

}
