package br.com.controle.certo.infrastructure.gateway.user;

import br.com.controle.certo.application.gateway.user.DeleteUserGateway;
import br.com.controle.certo.infrastructure.repository.auth.DbUserAuthRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserGatewayImpl implements DeleteUserGateway {

    @Autowired
    private DbUserRepository repository;
    @Autowired
    private DbUserAuthRepository authRepository;

    @Override
    public void deleteUserByUserDocument(Integer id) {
        DbUser result = repository.findById(id).orElseGet(null);
        repository.deleteUserById(id);
        authRepository.deleteUserAuthByUserDocument(result.getDocumentNumber());
    }
}
