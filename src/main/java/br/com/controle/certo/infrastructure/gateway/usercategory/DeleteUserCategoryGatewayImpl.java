package br.com.controle.certo.infrastructure.gateway.usercategory;

import br.com.controle.certo.application.gateway.usercategory.DeleteUserCategoryGateway;
import br.com.controle.certo.domain.entities.UserCategoryEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbUserCategoryRepository;
import br.com.controle.certo.infrastructure.repository.model.DbUserCategoryId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserCategoryGatewayImpl implements DeleteUserCategoryGateway {

    @Autowired
    private DbUserCategoryRepository repository;

    @Override
    public void deleteUserCategoryGateway(UserCategoryEntity body) {
        DbUserCategoryId userCategoryId = new DbUserCategoryId();
        userCategoryId.setDbCategory(body.getDbCategory());
        userCategoryId.setDbUser(body.getDbUser());
        repository.deleteById(userCategoryId);
    }
}
