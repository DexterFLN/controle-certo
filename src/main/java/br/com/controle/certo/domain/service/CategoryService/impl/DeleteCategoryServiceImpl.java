package br.com.controle.certo.domain.service.CategoryService.impl;

import br.com.controle.certo.application.gateway.category.DeleteCategoryGateway;
import br.com.controle.certo.application.gateway.usercategory.DeleteUserCategoryGateway;
import br.com.controle.certo.application.gateway.usercategory.GetUserCategoryGateway;
import br.com.controle.certo.domain.entities.UserCategoryEntity;
import br.com.controle.certo.domain.service.CategoryService.DeleteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCategoryServiceImpl implements DeleteCategoryService {
    @Autowired
    private DeleteUserCategoryGateway deleteUserCategoryGateway;
    @Autowired
    private GetUserCategoryGateway getUserCategoryGateway;
    @Autowired
    private DeleteCategoryGateway deleteCategoryGateway;

    @Override
    public void deleteCategory(String document, Integer idCategory) {
        UserCategoryEntity userCategory = getUserCategoryGateway.getUserCategory(document, idCategory);
        deleteUserCategoryGateway.deleteUserCategoryGateway(userCategory);
    }
}
