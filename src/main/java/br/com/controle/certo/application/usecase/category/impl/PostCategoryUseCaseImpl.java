package br.com.controle.certo.application.usecase.category.impl;

import br.com.controle.certo.application.gateway.category.GetCategoryGateway;
import br.com.controle.certo.application.gateway.category.PostCategoryGateway;
import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.application.gateway.usercategory.PostUserCategoryGateway;
import br.com.controle.certo.application.usecase.category.PostCategoryUseCase;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.UserCategoryEntity;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.handler.CategoryException;
import br.com.controle.certo.infrastructure.entrypoint.handler.UserException;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static br.com.controle.certo.application.mapper.CategoryUseCaseMapper.categoryEntityToResponseCategory;
import static br.com.controle.certo.infrastructure.mappers.CategoryMapper.categoryEntityToDbCategory;
import static br.com.controle.certo.infrastructure.mappers.UserMapper.userEntityToDbUser;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class PostCategoryUseCaseImpl implements PostCategoryUseCase {
    @Autowired
    private PostCategoryGateway gateway;
    @Autowired
    private GetCategoryGateway getCategoryGateway;
    @Autowired
    private GetUserGateway userGateway;
    @Autowired
    private PostUserCategoryGateway userCategoryGateway;

    @Transactional
    @Override
    public ResponseCategory postCategory(RequestCategory body, String userDocument) {
        verifyCategoryNameExists(userDocument,body.getCategoryName());
        CategoryEntity result = saveUserCategory(userDocument, body);
        return categoryEntityToResponseCategory(result);
    }

    private void verifyCategoryNameExists( String userDocument, String categoryName) {
        CategoryEntity result = getCategoryGateway.getCategoryByIdAndName(userDocument, categoryName);
        if (nonNull(result)) {
            throw new CategoryException("Categoria já existe com esse nome.");
        }
    }

    private CategoryEntity saveUserCategory(String userDocument, RequestCategory body) {
        CategoryEntity result = gateway.postCategoryGateway(body);
        UserEntity user = userGateway.getUser(userDocument);

        if (isNull(user)) {
            throw new UserException("Usuário não encontrado.");
        }

        UserCategoryEntity userCategory = UserCategoryEntity.builder()
                .dbUser(userEntityToDbUser(user))
                .dbCategory(categoryEntityToDbCategory(result)).build();

        userCategoryGateway.postUserCategoryGateway(userCategory);

        return result;
    }
}