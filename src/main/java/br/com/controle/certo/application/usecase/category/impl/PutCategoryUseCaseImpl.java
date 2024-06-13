package br.com.controle.certo.application.usecase.category.impl;

import br.com.controle.certo.application.gateway.category.GetCategoryGateway;
import br.com.controle.certo.application.gateway.category.PutCategoryGateway;
import br.com.controle.certo.application.usecase.category.PutCategoryUseCase;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static br.com.controle.certo.application.mapper.CategoryUseCaseMapper.categoryEntityToResponseCategory;
import static java.util.Objects.nonNull;

@Component
public class PutCategoryUseCaseImpl implements PutCategoryUseCase {
    @Autowired
    GetCategoryGateway categoryByIdGateway;
    @Autowired
    PutCategoryGateway putCategoryGateway;

    @Override
    public ResponseCategory updateCategoryById(String document, Integer idCategory, RequestCategory body) {
        CategoryEntity category = categoryByIdGateway.getCategoryById(document, idCategory);
        category.setCategoryName(nonNull(body.getCategoryName()) ? body.getCategoryName() : category.getCategoryName());
        category.setCategoryDescription(nonNull(body.getCategoryDescription()) ? body.getCategoryDescription() : category.getCategoryDescription());
        CategoryEntity response = putCategoryGateway.updateCategoryById(idCategory, category);
        return categoryEntityToResponseCategory(response);
    }
}
