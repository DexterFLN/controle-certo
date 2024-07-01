package br.com.controle.certo.application.usecase.user.impl;

import br.com.controle.certo.application.gateway.user.DeleteUserGateway;
import br.com.controle.certo.application.usecase.user.DeleteUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    @Autowired
    private DeleteUserGateway deleteUserGateway;

    @Override
    public void deleteUserByUserDocument(Integer id) {
        deleteUserGateway.deleteUserByUserDocument(id);
    }
}
