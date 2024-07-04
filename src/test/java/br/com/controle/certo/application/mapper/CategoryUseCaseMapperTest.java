package br.com.controle.certo.application.mapper;

import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryUseCaseMapperTest {

    @Test
    public void testCategoryEntityToResponseCategory_SingleEntity() {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .idCategory(1)
                .categoryName("Test Category")
                .categoryDescription("Test Description")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .build();

        ResponseCategory responseCategory = CategoryUseCaseMapper.categoryEntityToResponseCategory(categoryEntity);

        assertNotNull(responseCategory);
        assertEquals(categoryEntity.getIdCategory(), responseCategory.getIdCategory());
        assertEquals(categoryEntity.getCategoryName(), responseCategory.getCategoryName());
        assertEquals(categoryEntity.getCategoryDescription(), responseCategory.getCategoryDescription());
        assertEquals(categoryEntity.getDhCreate(), responseCategory.getDhCreate());
        assertEquals(categoryEntity.getDhUpdate(), responseCategory.getDhUpdate());
    }

    @Test
    public void testCategoryEntityToResponseCategory_ListOfEntities() {
        CategoryEntity categoryEntity1 = CategoryEntity.builder()
                .idCategory(1)
                .categoryName("Test Category 1")
                .categoryDescription("Test Description 1")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .build();

        CategoryEntity categoryEntity2 = CategoryEntity.builder()
                .idCategory(2)
                .categoryName("Test Category 2")
                .categoryDescription("Test Description 2")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .build();

        List<CategoryEntity> categoryEntities = Arrays.asList(categoryEntity1, categoryEntity2);

        List<ResponseCategory> responseCategories = CategoryUseCaseMapper.categoryEntityToResponseCategory(categoryEntities);

        assertNotNull(responseCategories);
        assertEquals(2, responseCategories.size());

        ResponseCategory responseCategory1 = responseCategories.get(0);
        assertEquals(categoryEntity1.getIdCategory(), responseCategory1.getIdCategory());
        assertEquals(categoryEntity1.getCategoryName(), responseCategory1.getCategoryName());
        assertEquals(categoryEntity1.getCategoryDescription(), responseCategory1.getCategoryDescription());
        assertEquals(categoryEntity1.getDhCreate(), responseCategory1.getDhCreate());
        assertEquals(categoryEntity1.getDhUpdate(), responseCategory1.getDhUpdate());

        ResponseCategory responseCategory2 = responseCategories.get(1);
        assertEquals(categoryEntity2.getIdCategory(), responseCategory2.getIdCategory());
        assertEquals(categoryEntity2.getCategoryName(), responseCategory2.getCategoryName());
        assertEquals(categoryEntity2.getCategoryDescription(), responseCategory2.getCategoryDescription());
        assertEquals(categoryEntity2.getDhCreate(), responseCategory2.getDhCreate());
        assertEquals(categoryEntity2.getDhUpdate(), responseCategory2.getDhUpdate());
    }

    @Test
    public void testCategoryEntityToResponseCategory_ListNullInput() {
        List<ResponseCategory> responseCategories = CategoryUseCaseMapper.categoryEntityToResponseCategory(Collections.emptyList());
        assertEquals(0, responseCategories.size());
    }
}
