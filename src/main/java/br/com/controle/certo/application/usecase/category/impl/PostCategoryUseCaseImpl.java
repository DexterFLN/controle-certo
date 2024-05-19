package br.com.controle.certo.application.usecase.category.impl;

import br.com.controle.certo.application.gateway.PostCategoryGateway;
import br.com.controle.certo.application.usecase.category.PostCategoryUseCase;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.service.CategoryService.PostCategoryService;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostCategoryUseCaseImpl implements PostCategoryUseCase {
    @Autowired
    private PostCategoryGateway gateway;
    @Autowired
    private PostCategoryService service;

    @Override
    public void saveCategory(RequestCategory body, String userDocument) {
        service.saveCategoryUser(gateway.postCategoryGateway(body), userDocument);
    }
}
