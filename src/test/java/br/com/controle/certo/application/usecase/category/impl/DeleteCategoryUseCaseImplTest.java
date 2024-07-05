package br.com.controle.certo.application.usecase.category.impl;

import br.com.controle.certo.domain.service.CategoryService.DeleteCategoryService;
import br.com.controle.certo.application.usecase.category.DeleteCategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryUseCaseImplTest {

    @Mock
    private DeleteCategoryService deleteCategoryService;

    @InjectMocks
    private DeleteCategoryUseCaseImpl deleteCategoryUseCase;

    private String document;
    private Integer idCategory;

    @BeforeEach
    public void setUp() {
        document = "12345678901";
        idCategory = 1;
    }

    @Test
    public void testDeleteCategoryById() {
        deleteCategoryUseCase.deleteCategoryById(document, idCategory);

        verify(deleteCategoryService, times(1)).deleteCategory(document, idCategory);
    }
}
