package br.com.controle.certo.infrastructure.gateway.user;

import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.controle.certo.infrastructure.mappers.UserMapper.dbUserToUserEntity;

@Component
public class GetUserGatewayImpl implements GetUserGateway {
    @Autowired
    private DbUserRepository repository;

    @Override
    public  UserEntity getUserByUserDocument(String userDocument) {
        DbUser result = repository.getUserByDocument(userDocument);
        return dbUserToUserEntity(result);
    }
    @Override
    public List<UserEntity> getUserAll() {
        List<DbUser> result = repository.getAllUsers();
        return dbUserToUserEntity(result);
    }
}
