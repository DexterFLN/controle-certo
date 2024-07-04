package br.com.controle.certo.application.usecase.user.impl;

import br.com.controle.certo.application.gateway.user.PostUserGateway;
import br.com.controle.certo.application.usecase.user.PostUserUseCase;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUser;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.controle.certo.application.mapper.UserUseCaseMapper.userEntityToResponseUser;

@Component
public class PostUserUseCaseImpl implements PostUserUseCase {

    @Autowired
    private PostUserGateway userGateway;

    @Override
    public ResponseUser postUser(RequestUser body, String userDocument) {
        UserEntity result = userGateway.postUser(body, userDocument);
        return userEntityToResponseUser(result);
    }
}
