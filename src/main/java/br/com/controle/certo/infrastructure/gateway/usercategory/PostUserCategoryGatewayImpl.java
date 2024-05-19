package br.com.controle.certo.infrastructure.gateway.usercategory;

import br.com.controle.certo.application.gateway.usercategory.PostUserCategoryGateway;
import br.com.controle.certo.domain.entities.UserCategoryEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbUserCategoryRepository;
import br.com.controle.certo.infrastructure.repository.model.DbUserCategory;
import br.com.controle.certo.infrastructure.repository.model.DbUserCategoryId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostUserCategoryGatewayImpl implements PostUserCategoryGateway {
    @Autowired
    private DbUserCategoryRepository repository;

    @Override
    public void postUserCategoryGateway(UserCategoryEntity body) {
        DbUserCategoryId userCategoryId = new DbUserCategoryId();
        userCategoryId.setDbCategory(body.getDbCategory());
        userCategoryId.setDbUser(body.getDbUser());
        DbUserCategory userCategory = new DbUserCategory();
        userCategory.setDbUserCategoryId(userCategoryId);
        repository.save(userCategory);
    }
}
