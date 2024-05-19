package br.com.controle.certo.application.usecase.category.impl;

import br.com.controle.certo.application.gateway.GetCategoryGateway;
import br.com.controle.certo.application.gateway.PutCategoryGateway;
import br.com.controle.certo.application.usecase.category.PostCategoryUseCase;
import br.com.controle.certo.application.usecase.category.PutCategoryUseCase;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.controle.certo.application.mapper.CategoryUseCaseMapper.categoryEntityToResponseCategory;

@Component
public class PutCategoryUseCaseImpl implements PutCategoryUseCase {
    @Autowired
    GetCategoryGateway categoryByIdGateway;
    @Autowired
    PutCategoryGateway putCategoryGateway;

    @Override
    public ResponseCategory updateCategoryById(String document, Integer idCategory, RequestCategory body) {
        CategoryEntity category = categoryByIdGateway.getCategoryById(document, idCategory);
        category.setCategoryName(body.getCategoryName());
        category.setCategoryDescription(body.getCategoryDescription());
        CategoryEntity response = putCategoryGateway.updateCategoryById(idCategory, category);

        return categoryEntityToResponseCategory(response);
    }
}
