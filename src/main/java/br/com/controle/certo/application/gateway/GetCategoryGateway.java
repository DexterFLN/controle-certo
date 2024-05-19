package br.com.controle.certo.application.gateway;

import br.com.controle.certo.domain.entities.CategoryEntity;

import java.util.List;

public interface GetCategoryGateway {
    CategoryEntity getCategoryById(String document, Integer IdCategory);

    List<CategoryEntity> getAllCategory(String document);
}
