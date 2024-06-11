package br.com.controle.certo.domain.service.CategoryService.impl;

import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.application.gateway.usercategory.PostUserCategoryGateway;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.UserCategoryEntity;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.domain.service.CategoryService.PostCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.controle.certo.infrastructure.mappers.CategoryMapper.categoryEntityToDbCategory;
import static br.com.controle.certo.infrastructure.mappers.UserMapper.userEntityToDbUser;

@Component
public class PostCategoryServiceImpl implements PostCategoryService {
    @Autowired
    private GetUserGateway userGateway;
    @Autowired
    private PostUserCategoryGateway userCategoryGateway;
    @Override
    public void saveCategoryUser(CategoryEntity body, String document) {
        UserEntity user = userGateway.getUser(document);
        UserCategoryEntity userCategory = UserCategoryEntity.builder()
                .dbUser(userEntityToDbUser(user))
                .dbCategory(categoryEntityToDbCategory(body)).build();

        userCategoryGateway.postUserCategoryGateway(userCategory);
    }
}
