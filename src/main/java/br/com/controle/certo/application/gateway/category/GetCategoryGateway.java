package br.com.controle.certo.application.gateway.category;

import br.com.controle.certo.domain.entities.CategoryEntity;

import java.util.List;

public interface GetCategoryGateway {
    CategoryEntity getCategoryById(String document, Integer IdCategory);
    CategoryEntity getCategoryByIdAndName(String document, String categoryName);
    List<CategoryEntity> getAllCategory(String document);
}