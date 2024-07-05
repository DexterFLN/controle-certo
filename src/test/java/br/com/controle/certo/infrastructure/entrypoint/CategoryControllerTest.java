package br.com.controle.certo.infrastructure.entrypoint;

import br.com.controle.certo.application.usecase.category.*;
import br.com.controle.certo.infrastructure.config.resourceserver.AuthJwtClaim;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Mock
    private PostCategoryUseCase postCategoryUseCase;

    @Mock
    private GetCategoryAllUseCase getCategoryAllUseCase;

    @Mock
    private GetCategoryUseCase getCategoryUseCase;

    @Mock
    private DeleteCategoryUseCase deleteCategoryUseCase;

    @Mock
    private PutCategoryUseCase putCategoryUseCase;

    @Mock
    private AuthJwtClaim authJwtClaim;

    @InjectMocks
    private CategoryController categoryController;

    private String userDocument;

    @BeforeEach
    public void setUp() {
        userDocument = "12345678901";
        when(authJwtClaim.getDocumentNumberUserFromJwt()).thenReturn(userDocument);
    }

    @Test
    public void testPostCategory() {
        RequestCategory requestCategory = new RequestCategory("Category Name", "Category Description");
        ResponseCategory responseCategory = new ResponseCategory(1, "Category Name", "Category Description", null, null);

        when(postCategoryUseCase.postCategory(any(RequestCategory.class), eq(userDocument))).thenReturn(responseCategory);

        ResponseEntity<?> response = categoryController.postCategory(requestCategory);

        assertEquals(ResponseEntity.ok(responseCategory), response);
        verify(postCategoryUseCase, times(1)).postCategory(any(RequestCategory.class), eq(userDocument));
    }

    @Test
    public void testUpdateCategoryById() {
        Integer idCategory = 1;
        RequestCategory requestCategory = new RequestCategory("Updated Name", "Updated Description");

        ResponseEntity<?> response = categoryController.updateCategoryById(idCategory, requestCategory);

        verify(putCategoryUseCase, times(1)).updateCategoryById(eq(userDocument), eq(idCategory), any(RequestCategory.class));
        assertEquals(ResponseEntity.ok().build(), response);
    }

    @Test
    public void testDeleteCategoryById() {
        Integer idCategory = 1;

        ResponseEntity<?> response = categoryController.deleteCategoryById(idCategory);

        verify(deleteCategoryUseCase, times(1)).deleteCategoryById(eq(userDocument), eq(idCategory));
        assertEquals(ResponseEntity.noContent().build(), response);
    }
}
