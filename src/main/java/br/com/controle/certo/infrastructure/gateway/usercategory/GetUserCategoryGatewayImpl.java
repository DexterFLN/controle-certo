package br.com.controle.certo.infrastructure.gateway.usercategory;

import br.com.controle.certo.application.gateway.usercategory.GetUserCategoryGateway;
import br.com.controle.certo.domain.entities.UserCategoryEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbUserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.controle.certo.infrastructure.mappers.UserCategoryMapper.dbUserCategoryToUserCategoryEntity;

@Component
public class GetUserCategoryGatewayImpl implements GetUserCategoryGateway {

    @Autowired
    private DbUserCategoryRepository repository;

    @Override
    public UserCategoryEntity getUserCategory(String document, Integer idCategory) {
        return dbUserCategoryToUserCategoryEntity(repository.getUserCategoryByDocumentAndIdCategory(document, idCategory));
    }
}
