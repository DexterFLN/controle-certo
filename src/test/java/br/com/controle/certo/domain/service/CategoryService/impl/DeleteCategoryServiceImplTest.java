package br.com.controle.certo.domain.service.CategoryService.impl;

import br.com.controle.certo.application.gateway.category.DeleteCategoryGateway;
import br.com.controle.certo.application.gateway.usercategory.DeleteUserCategoryGateway;
import br.com.controle.certo.application.gateway.usercategory.GetUserCategoryGateway;
import br.com.controle.certo.domain.entities.UserCategoryEntity;
import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteCategoryServiceImplTest {

    @Mock
    private DeleteUserCategoryGateway deleteUserCategoryGateway;

    @Mock
    private GetUserCategoryGateway getUserCategoryGateway;

    @Mock
    private DeleteCategoryGateway deleteCategoryGateway;

    @InjectMocks
    private DeleteCategoryServiceImpl deleteCategoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteCategory() {
        String document = "123456789";
        Integer idCategory = 1;

        DbUser dbUser = DbUser.builder().build();
        DbCategory dbCategory = DbCategory.builder().build();

        UserCategoryEntity userCategory = UserCategoryEntity.builder()
                .dbUser(dbUser)
                .dbCategory(dbCategory)
                .build();

        when(getUserCategoryGateway.getUserCategory(document, idCategory)).thenReturn(userCategory);

        deleteCategoryService.deleteCategory(document, idCategory);

        verify(deleteUserCategoryGateway).deleteUserCategoryGateway(any(UserCategoryEntity.class));
    }
}
