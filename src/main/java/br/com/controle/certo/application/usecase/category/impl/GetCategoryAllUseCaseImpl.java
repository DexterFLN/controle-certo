package br.com.controle.certo.application.usecase.category.impl;

import br.com.controle.certo.application.gateway.category.GetCategoryGateway;
import br.com.controle.certo.application.usecase.category.GetCategoryAllUseCase;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.controle.certo.application.mapper.CategoryUseCaseMapper.categoryEntityToResponseCategory;

@Component
public class GetCategoryAllUseCaseImpl implements GetCategoryAllUseCase {

    @Autowired
    private GetCategoryGateway categoryByIdGateway;

    @Override
    public List<ResponseCategory> getAllCategory(String userDocument) {
        return categoryEntityToResponseCategory(categoryByIdGateway.getAllCategory(userDocument));
    }
}
