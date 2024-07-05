package br.com.controle.certo.application.usecase.category.impl;

import br.com.controle.certo.application.gateway.category.GetCategoryGateway;
import br.com.controle.certo.application.gateway.category.PostCategoryGateway;
import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.application.gateway.usercategory.PostUserCategoryGateway;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.handler.CategoryException;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PostCategoryUseCaseImplTest {

    @InjectMocks
    private PostCategoryUseCaseImpl postCategoryUseCase;

    @Mock
    private PostCategoryGateway postCategoryGateway;

    @Mock
    private GetCategoryGateway getCategoryGateway;

    @Mock
    private GetUserGateway getUserGateway;

    @Mock
    private PostUserCategoryGateway postUserCategoryGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPostCategory_Success() {
        // Arrange
        RequestCategory requestCategory = RequestCategory.builder()
                .categoryName("Test Category")
                .categoryDescription("Test Description")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .documentNumber("12345678900")
                .build();

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryName("Test Category")
                .build();

        when(getUserGateway.getUserByUserDocument(anyString())).thenReturn(userEntity);
        when(postCategoryGateway.postCategoryGateway(any(RequestCategory.class))).thenReturn(categoryEntity);
        when(getCategoryGateway.getCategoryByIdAndName(anyString(), anyString())).thenReturn(null);

        // Act
        ResponseCategory response = postCategoryUseCase.postCategory(requestCategory, "12345678900");

        // Assert
        assertNotNull(response);
        assertEquals("Test Category", response.getCategoryName());
        verify(getUserGateway, times(1)).getUserByUserDocument(anyString());
        verify(postCategoryGateway, times(1)).postCategoryGateway(any(RequestCategory.class));
        verify(postUserCategoryGateway, times(1)).postUserCategoryGateway(any());
    }

    @Test
    public void testPostCategory_CategoryExists() {
        // Arrange
        RequestCategory requestCategory = RequestCategory.builder()
                .categoryName("Test Category")
                .build();

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryName("Test Category")
                .build();

        when(getCategoryGateway.getCategoryByIdAndName(anyString(), anyString())).thenReturn(categoryEntity);

        // Act & Assert
        CategoryException exception = assertThrows(CategoryException.class, () -> {
            postCategoryUseCase.postCategory(requestCategory, "12345678900");
        });

        assertEquals("Categoria já existe com esse nome.", exception.getMessage());
        verify(getCategoryGateway, times(1)).getCategoryByIdAndName(anyString(), anyString());
        verify(getUserGateway, times(0)).getUserByUserDocument(anyString());
        verify(postCategoryGateway, times(0)).postCategoryGateway(any(RequestCategory.class));
        verify(postUserCategoryGateway, times(0)).postUserCategoryGateway(any());
    }
}
