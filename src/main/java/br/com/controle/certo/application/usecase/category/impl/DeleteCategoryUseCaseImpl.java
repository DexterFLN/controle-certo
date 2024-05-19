package br.com.controle.certo.application.usecase.category.impl;

import br.com.controle.certo.application.usecase.category.DeleteCategoryUseCase;
import br.com.controle.certo.domain.service.CategoryService.DeleteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {

    @Autowired
    private DeleteCategoryService deleteCategoryService;

    @Override
    public void deleteCategoryById(String document, Integer idCategory) {
        deleteCategoryService.deleteCategory(document, idCategory);
    }
}
