package br.com.controle.certo.application.gateway.usercategory;

import br.com.controle.certo.domain.entities.UserCategoryEntity;

public interface GetUserCategoryGateway {

    UserCategoryEntity getUserCategory(String document, Integer idCategory);
}
