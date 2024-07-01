package br.com.controle.certo.application.usecase.user.impl;

import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.application.gateway.user.PutUserGateway;
import br.com.controle.certo.application.usecase.user.PutUserUseCase;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.handler.UserException;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestPutUser;
import br.com.controle.certo.infrastructure.repository.auth.DbUserAuth;
import br.com.controle.certo.infrastructure.repository.auth.DbUserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class PutUserUseCaseImpl implements PutUserUseCase {

    @Autowired
    private GetUserGateway userGateway;

    @Autowired
    private PutUserGateway putUserGateway;
    @Autowired
    private DbUserAuthRepository userAuthRepository;

    @Override
    public void putUserByUserDocument(RequestPutUser body, String userDocument) {
        UserEntity result = userGateway.getUserByUserDocument(userDocument);

        if (isNull(result)) {
            throw new UserException("Usuário não encontrado.");
        }

        if (nonNull(body.getUserName())) {
            result.setUserName(body.getUserName());
        }

        if (nonNull(body.getEmailUser())) {
            result.setEmailUser(body.getEmailUser());
            DbUserAuth userAuth = userAuthRepository.findByUsernameAndDhExcludeIsNull(userDocument).orElseThrow();
            userAuth.setEmailUser(body.getEmailUser());
            userAuthRepository.save(userAuth);
        }

        if (nonNull(body.getPhoneNumber())) {
            result.setPhoneNumber(body.getPhoneNumber());
        }

        if (nonNull(body.getCep())) {
            result.setCep(body.getCep());
        }

        if (nonNull(body.getUf())) {
            result.setUf(body.getUf());
        }

        if (nonNull(body.getCounty())) {
            result.setCounty(body.getCounty());
        }

        if (nonNull(body.getNeighborhood())) {
            result.setNeighborhood(body.getNeighborhood());
        }

        putUserGateway.putUserByUserDocument(result);
    }
}
