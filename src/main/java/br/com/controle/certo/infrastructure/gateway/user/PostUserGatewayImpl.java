package br.com.controle.certo.infrastructure.gateway.user;

import br.com.controle.certo.application.gateway.user.PostUserGateway;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUser;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.controle.certo.infrastructure.mappers.UserMapper.dbUserToUserEntity;
import static br.com.controle.certo.infrastructure.mappers.UserMapper.requestUserToDbUser;

@Component
public class PostUserGatewayImpl implements PostUserGateway {
    @Autowired
    private DbUserRepository repository;

    @Override
    public UserEntity postUser(RequestUser body, String userDocument) {
        DbUser request = requestUserToDbUser(body,userDocument);
        DbUser result = repository.save(request);
        return dbUserToUserEntity(result);
    }
}
