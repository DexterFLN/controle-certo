package br.com.controle.certo.application.usecase.user.impl;

import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.application.usecase.user.GetUserByUserDocumentUseCase;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.controle.certo.application.mapper.UserUseCaseMapper.userEntityToResponseUser;

@Component
public class GetUserByUserDocumentUseCaseImpl implements GetUserByUserDocumentUseCase {

    @Autowired
    private GetUserGateway userGateway;

    @Override
    public ResponseUser getUserByUserDocument(String userDocument) {
        UserEntity result = userGateway.getUserByUserDocument(userDocument);
        return userEntityToResponseUser(result);
    }
}
