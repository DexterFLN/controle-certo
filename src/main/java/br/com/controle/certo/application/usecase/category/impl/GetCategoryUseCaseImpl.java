package br.com.controle.certo.application.usecase.category.impl;

import br.com.controle.certo.application.gateway.category.GetCategoryGateway;
import br.com.controle.certo.application.usecase.category.GetCategoryUseCase;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.controle.certo.application.mapper.CategoryUseCaseMapper.categoryEntityToResponseCategory;

@Component
public class GetCategoryUseCaseImpl implements GetCategoryUseCase {

    @Autowired
    GetCategoryGateway categoryByIdGateway;

    @Override
    public ResponseCategory getCategoryById(String document, Integer idCategory) {
        return categoryEntityToResponseCategory(categoryByIdGateway.getCategoryById(document, idCategory));
    }
}
