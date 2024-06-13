package br.com.controle.certo.application.gateway.category;

import br.com.controle.certo.domain.entities.CategoryEntity;

public interface PutCategoryGateway {
    CategoryEntity updateCategoryById(Integer idCategory, CategoryEntity body);
}
