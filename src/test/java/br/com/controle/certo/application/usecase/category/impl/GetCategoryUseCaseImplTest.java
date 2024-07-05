//package br.com.controle.certo.application.usecase.category.impl;
//
//import br.com.controle.certo.application.gateway.category.GetCategoryGateway;
//import br.com.controle.certo.domain.entities.CategoryEntity;
//import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//public class GetCategoryUseCaseImplTest {
//
//    @InjectMocks
//    private GetCategoryUseCaseImpl getCategoryUseCase;
//
//    @Mock
//    private GetCategoryGateway getCategoryGateway;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetCategoryById_Success() {
//        String document = "12345678900";
//        Integer idCategory = 1;
//
//        CategoryEntity categoryEntity = CategoryEntity.builder()
//                .idCategory(idCategory)
//                .categoryName("Test Category")
//                .categoryDescription("Test Description")
//                .dhCreate(null)
//                .dhUpdate(null)
//                .build();
//
//        when(getCategoryGateway.getCategoryById(anyString(), anyInt())).thenReturn(categoryEntity);
//
//        ResponseCategory responseCategory = getCategoryUseCase.getCategoryById(document, idCategory);
//
//        assertEquals(idCategory, responseCategory.getIdCategory());
//        assertEquals("Test Category", responseCategory.getCategoryName());
//        assertEquals("Test Description", responseCategory.getCategoryDescription());
//    }
//
//    @Test
//    void testGetCategoryById_NotFound() {
//        String document = "12345678900";
//        Integer idCategory = 1;
//
//        when(getCategoryGateway.getCategoryById(anyString(), anyInt())).thenReturn(null);
//
//        ResponseCategory responseCategory = getCategoryUseCase.getCategoryById(document, idCategory);
//
//        assertNull(responseCategory);
//    }
//
//    @Test
//    void testGetCategoryById_NullDocument() {
//        String document = null;
//        Integer idCategory = 1;
//
//        when(getCategoryGateway.getCategoryById(anyString(), anyInt())).thenReturn(null);
//
//        ResponseCategory responseCategory = getCategoryUseCase.getCategoryById(document, idCategory);
//
//        assertNull(responseCategory);
//    }
//
//    @Test
//    void testGetCategoryById_NullIdCategory() {
//        String document = "12345678900";
//        Integer idCategory = null;
//
//        when(getCategoryGateway.getCategoryById(anyString(), anyInt())).thenReturn(null);
//
//        ResponseCategory responseCategory = getCategoryUseCase.getCategoryById(document, idCategory);
//
//        assertNull(responseCategory);
//    }
//
//    @Test
//    void testGetCategoryById_EmptyDocument() {
//        String document = "";
//        Integer idCategory = 1;
//
//        when(getCategoryGateway.getCategoryById(anyString(), anyInt())).thenReturn(null);
//
//        ResponseCategory responseCategory = getCategoryUseCase.getCategoryById(document, idCategory);
//
//        assertNull(responseCategory);
//    }
//
//    @Test
//    void testGetCategoryById_ZeroIdCategory() {
//        String document = "12345678900";
//        Integer idCategory = 0;
//
//        when(getCategoryGateway.getCategoryById(anyString(), anyInt())).thenReturn(null);
//
//        ResponseCategory responseCategory = getCategoryUseCase.getCategoryById(document, idCategory);
//
//        assertNull(responseCategory);
//    }
//
//    @Test
//    void testGetCategoryById_NegativeIdCategory() {
//        String document = "12345678900";
//        Integer idCategory = -1;
//
//        when(getCategoryGateway.getCategoryById(anyString(), anyInt())).thenReturn(null);
//
//        ResponseCategory responseCategory = getCategoryUseCase.getCategoryById(document, idCategory);
//
//        assertNull(responseCategory);
//    }
//}
