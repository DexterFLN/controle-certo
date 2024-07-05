package br.com.controle.certo.infrastructure.gateway.category;

import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class PutCategoryGatewayImplTest {

    @Mock
    private DbCategoryRepository repository;

    @InjectMocks
    private PutCategoryGatewayImpl putCategoryGateway;

    @Test
    public void testUpdateCategoryById() {
        Integer idCategory = 1;
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .idCategory(idCategory)
                .categoryName("Updated Category Name")
                .categoryDescription("Updated Category Description")
                .build();

        putCategoryGateway.updateCategoryById(idCategory, categoryEntity);

        verify(repository, times(1)).updateCategoryById(
                categoryEntity.getCategoryName(),
                categoryEntity.getCategoryDescription(),
                idCategory
        );
    }
}
