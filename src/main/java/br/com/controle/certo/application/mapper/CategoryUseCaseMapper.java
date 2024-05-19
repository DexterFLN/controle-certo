package br.com.controle.certo.application.mapper;

import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;

import java.util.List;

public class CategoryUseCaseMapper {

    public static ResponseCategory categoryEntityToResponseCategory(CategoryEntity body) {
        return ResponseCategory.builder()
                .idCategory(body.getIdCategory())
                .categoryDescription(body.getCategoryDescription())
                .categoryName(body.getCategoryName())
                .dhCreate(body.getDhCreate())
                .dhUpdate(body.getDhUpdate())
                .build();
    }

    public static List<ResponseCategory> categoryEntityToResponseCategory(List<CategoryEntity> body) {
        return body.stream().map(m ->
                ResponseCategory.builder()
                        .idCategory(m.getIdCategory())
                        .categoryDescription(m.getCategoryDescription())
                        .categoryName(m.getCategoryName())
                        .dhCreate(m.getDhCreate())
                        .dhUpdate(m.getDhUpdate())
                        .build()).toList();
    }
}
