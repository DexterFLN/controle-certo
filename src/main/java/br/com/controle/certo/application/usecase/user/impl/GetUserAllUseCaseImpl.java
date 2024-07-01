package br.com.controle.certo.application.usecase.user.impl;

import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.application.usecase.user.GetUserAllUseCase;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.controle.certo.application.mapper.UserUseCaseMapper.userEntityToResponseUser;

@Component
public class GetUserAllUseCaseImpl implements GetUserAllUseCase {
    @Autowired
    private GetUserGateway userGateway;

    @Override
    public List<ResponseUser> getUserAll() {
        List<UserEntity> result = userGateway.getUserAll();
        return userEntityToResponseUser(result);
    }
}
