package br.com.controle.certo.application.usecase.category.impl;

import br.com.controle.certo.application.gateway.category.GetCategoryGateway;
import br.com.controle.certo.application.gateway.category.PutCategoryGateway;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PutCategoryUseCaseImplTest {

    @InjectMocks
    private PutCategoryUseCaseImpl putCategoryUseCase;

    @Mock
    private GetCategoryGateway getCategoryGateway;

    @Mock
    private PutCategoryGateway putCategoryGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateCategoryById_Success() {
        String document = "12345678900";
        Integer idCategory = 1;
        RequestCategory requestCategory = RequestCategory.builder()
                .categoryName("Updated Category Name")
                .categoryDescription("Updated Category Description")
                .build();

        CategoryEntity existingCategory = CategoryEntity.builder()
                .idCategory(idCategory)
                .categoryName("Old Category Name")
                .categoryDescription("Old Category Description")
                .build();

        CategoryEntity updatedCategory = CategoryEntity.builder()
                .idCategory(idCategory)
                .categoryName("Updated Category Name")
                .categoryDescription("Updated Category Description")
                .build();

        when(getCategoryGateway.getCategoryById(anyString(), anyInt())).thenReturn(existingCategory);
        when(putCategoryGateway.updateCategoryById(anyInt(), any(CategoryEntity.class))).thenReturn(updatedCategory);

        ResponseCategory responseCategory = putCategoryUseCase.updateCategoryById(document, idCategory, requestCategory);

        assertEquals("Updated Category Name", responseCategory.getCategoryName());
        assertEquals("Updated Category Description", responseCategory.getCategoryDescription());
        verify(getCategoryGateway, times(1)).getCategoryById(anyString(), anyInt());
        verify(putCategoryGateway, times(1)).updateCategoryById(anyInt(), any(CategoryEntity.class));
    }

    @Test
    void testUpdateCategoryById_PartialUpdate() {
        String document = "12345678900";
        Integer idCategory = 1;
        RequestCategory requestCategory = RequestCategory.builder()
                .categoryName("Updated Category Name")
                .build();

        CategoryEntity existingCategory = CategoryEntity.builder()
                .idCategory(idCategory)
                .categoryName("Old Category Name")
                .categoryDescription("Old Category Description")
                .build();

        CategoryEntity updatedCategory = CategoryEntity.builder()
                .idCategory(idCategory)
                .categoryName("Updated Category Name")
                .categoryDescription("Old Category Description")
                .build();

        when(getCategoryGateway.getCategoryById(anyString(), anyInt())).thenReturn(existingCategory);
        when(putCategoryGateway.updateCategoryById(anyInt(), any(CategoryEntity.class))).thenReturn(updatedCategory);

        ResponseCategory responseCategory = putCategoryUseCase.updateCategoryById(document, idCategory, requestCategory);

        assertEquals("Updated Category Name", responseCategory.getCategoryName());
        assertEquals("Old Category Description", responseCategory.getCategoryDescription());
        verify(getCategoryGateway, times(1)).getCategoryById(anyString(), anyInt());
        verify(putCategoryGateway, times(1)).updateCategoryById(anyInt(), any(CategoryEntity.class));
    }
}
