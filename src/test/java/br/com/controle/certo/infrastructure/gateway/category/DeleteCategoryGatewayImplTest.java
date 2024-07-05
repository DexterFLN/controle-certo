package br.com.controle.certo.infrastructure.gateway.category;

import br.com.controle.certo.infrastructure.repository.impl.DbCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryGatewayImplTest {

    @Mock
    private DbCategoryRepository repository;

    @InjectMocks
    private DeleteCategoryGatewayImpl deleteCategoryGateway;

    @Test
    public void testDeleteCategoryById() {
        Integer idCategory = 1;

        deleteCategoryGateway.deleteCategoryById(idCategory);

        verify(repository, times(1)).deleteById(idCategory);
    }
}
