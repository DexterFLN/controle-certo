package br.com.controle.certo.application.usecase.category.impl;

import br.com.controle.certo.application.gateway.category.GetCategoryGateway;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetCategoryAllUseCaseImplTest {

    @Mock
    private GetCategoryGateway getCategoryGateway;

    @InjectMocks
    private GetCategoryAllUseCaseImpl getCategoryAllUseCase;

    private CategoryEntity categoryEntity1;
    private CategoryEntity categoryEntity2;
    private String userDocument;

    @BeforeEach
    public void setUp() {
        userDocument = "12345678901";

        categoryEntity1 = CategoryEntity.builder()
                .idCategory(1)
                .categoryName("Category 1")
                .categoryDescription("Description 1")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .build();

        categoryEntity2 = CategoryEntity.builder()
                .idCategory(2)
                .categoryName("Category 2")
                .categoryDescription("Description 2")
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .build();
    }

    @Test
    public void testGetAllCategory() {
        List<CategoryEntity> categoryList = Arrays.asList(categoryEntity1, categoryEntity2);
        when(getCategoryGateway.getAllCategory(userDocument)).thenReturn(categoryList);

        List<ResponseCategory> responseCategoryList = getCategoryAllUseCase.getAllCategory(userDocument);

        verify(getCategoryGateway, times(1)).getAllCategory(userDocument);
        assertNotNull(responseCategoryList);
        assertEquals(2, responseCategoryList.size());

        ResponseCategory responseCategory1 = responseCategoryList.get(0);
        assertEquals(categoryEntity1.getIdCategory(), responseCategory1.getIdCategory());
        assertEquals(categoryEntity1.getCategoryName(), responseCategory1.getCategoryName());
        assertEquals(categoryEntity1.getCategoryDescription(), responseCategory1.getCategoryDescription());
        assertEquals(categoryEntity1.getDhCreate(), responseCategory1.getDhCreate());
        assertEquals(categoryEntity1.getDhUpdate(), responseCategory1.getDhUpdate());

        ResponseCategory responseCategory2 = responseCategoryList.get(1);
        assertEquals(categoryEntity2.getIdCategory(), responseCategory2.getIdCategory());
        assertEquals(categoryEntity2.getCategoryName(), responseCategory2.getCategoryName());
        assertEquals(categoryEntity2.getCategoryDescription(), responseCategory2.getCategoryDescription());
        assertEquals(categoryEntity2.getDhCreate(), responseCategory2.getDhCreate());
        assertEquals(categoryEntity2.getDhUpdate(), responseCategory2.getDhUpdate());
    }
}
