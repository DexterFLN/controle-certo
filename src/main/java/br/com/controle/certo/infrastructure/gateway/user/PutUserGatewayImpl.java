package br.com.controle.certo.infrastructure.gateway.user;

import br.com.controle.certo.application.gateway.user.PutUserGateway;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.controle.certo.infrastructure.mappers.UserMapper.userEntityToDbUser;

@Component
public class PutUserGatewayImpl implements PutUserGateway {

    @Autowired
    private DbUserRepository repository;

    @Override
    public void putUserByUserDocument(UserEntity body) {
        DbUser result = userEntityToDbUser(body);
        repository.save(result);
    }
}
