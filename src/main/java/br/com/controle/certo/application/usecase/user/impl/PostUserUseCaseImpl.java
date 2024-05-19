package br.com.controle.certo.application.usecase.user.impl;

import br.com.controle.certo.application.gateway.user.PostUserGateway;
import br.com.controle.certo.application.usecase.user.PostUserUseCase;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostUserUseCaseImpl implements PostUserUseCase {

    @Autowired
    private PostUserGateway userGateway;

    @Override
    public void postUser(RequestUser body, String document) {
        userGateway.postUser(body, document);
    }
}
