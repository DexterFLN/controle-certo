package br.com.controle.certo.infrastructure.mappers;

import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import br.com.controle.certo.infrastructure.repository.model.DbCategory;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class CategoryMapper {

    public static DbCategory requestToDbCategory(RequestCategory body) {
        DbCategory dbCategory = new DbCategory();
        dbCategory.setCategoryDescription(body.getCategoryDescription());
        dbCategory.setCategoryName(body.getCategoryName());

        return dbCategory;
    }

    public static DbCategory categoryEntityToDbCategory(CategoryEntity body) {
        DbCategory dbCategory = new DbCategory();
        dbCategory.setIdCategory(body.getIdCategory());
        dbCategory.setDhCreate(body.getDhCreate());
        dbCategory.setDhUpdate(body.getDhUpdate());
        dbCategory.setCategoryDescription(body.getCategoryDescription());
        dbCategory.setCategoryName(body.getCategoryName());
        return dbCategory;
    }

    public static CategoryEntity dbCategoryToCategoryEntity(DbCategory body) {
        return nonNull(body) ? CategoryEntity.builder()
                .idCategory(body.getIdCategory())
                .categoryDescription(body.getCategoryDescription())
                .categoryName(body.getCategoryName())
                .dhCreate(body.getDhCreate())
                .dhUpdate(body.getDhUpdate())
                .build() : null;
    }

    public static List<CategoryEntity> dbCategoryToCategoryEntity(List<DbCategory> body) {
        return body.stream().map(m ->
                CategoryEntity.builder()
                        .idCategory(m.getIdCategory())
                        .categoryDescription(m.getCategoryDescription())
                        .categoryName(m.getCategoryName())
                        .dhCreate(m.getDhCreate())
                        .dhUpdate(m.getDhUpdate())
                        .build()).toList();
    }
}