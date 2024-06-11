package br.com.controle.certo.infrastructure.gateway.user;

import br.com.controle.certo.application.gateway.user.PostUserGateway;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUser;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostUserGatewayImpl implements PostUserGateway {

    @Autowired
    private DbUserRepository repository;

    @Override
    public void postUser(RequestUser body, String document) {
        DbUser user = new DbUser();
        user.setDocumentNumber(document);
        user.setEmailUser(body.getEmailUser());
        user.setPhoneNumber(body.getPhoneNumber());
        user.setUf(body.getUf());
        user.setCounty(body.getCounty());
        user.setAcceptTerms(body.getAcceptTerms());
        repository.save(user);
    }
}
