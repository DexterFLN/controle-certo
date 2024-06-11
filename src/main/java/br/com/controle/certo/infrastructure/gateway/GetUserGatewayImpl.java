package br.com.controle.certo.infrastructure.gateway;

import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.controle.certo.infrastructure.mappers.UserMapper.dbUserToUserEntity;

@Component
public class GetUserGatewayImpl implements GetUserGateway {
    @Autowired
    private DbUserRepository repository;

    @Override
    public UserEntity getUser(String document) {
        DbUser result = repository.getUserByDocument(document);
        return dbUserToUserEntity(result);
    }
}
