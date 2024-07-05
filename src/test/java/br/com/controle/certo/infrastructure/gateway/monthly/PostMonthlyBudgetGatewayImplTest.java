package br.com.controle.certo.infrastructure.gateway.monthly;

import br.com.controle.certo.infrastructure.entrypoint.handler.CategoryException;
import br.com.controle.certo.infrastructure.entrypoint.handler.MonthlyBudgetException;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestItemBudget;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestMonthlyBudget;
import br.com.controle.certo.infrastructure.repository.impl.DbCategoryRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbItemBudgetRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbMonthlyBudgetRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import br.com.controle.certo.infrastructure.repository.model.DbItemBudget;
import br.com.controle.certo.infrastructure.repository.model.DbMonthlyBudget;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PostMonthlyBudgetGatewayImplTest {

    @InjectMocks
    private PostMonthlyBudgetGatewayImpl postMonthlyBudgetGateway;

    @Mock
    private DbMonthlyBudgetRepository repository;

    @Mock
    private DbCategoryRepository categoryRepository;

    @Mock
    private DbUserRepository userRepository;

    @Mock
    private DbItemBudgetRepository itemRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPostMonthlyBudget_Success() {
        String userDocument = "user123";
        RequestItemBudget itemBudget = RequestItemBudget.builder()
                .dbCategory(1)
                .expenseValue(100.0)
                .build();

        RequestMonthlyBudget request = RequestMonthlyBudget.builder()
                .monthlyIncome(5000.0)
                .monthlyReference("07/2024")
                .itemBudgetList(Collections.singletonList(itemBudget))
                .build();

        DbCategory dbCategory = new DbCategory();
        dbCategory.setIdCategory(1);

        when(categoryRepository.getAllCategoryByDocument(userDocument)).thenReturn(Collections.singletonList(dbCategory));
        when(userRepository.getUserByDocument(userDocument)).thenReturn(new DbUser());
        when(repository.save(any())).thenReturn(new DbMonthlyBudget());

        postMonthlyBudgetGateway.postMonthlyBudget(request, userDocument);

        verify(repository, times(1)).save(any(DbMonthlyBudget.class));
        verify(itemRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testPostMonthlyBudget_CategoryNotFound() {
        String userDocument = "user123";
        RequestItemBudget itemBudget = RequestItemBudget.builder()
                .dbCategory(2)
                .expenseValue(100.0)
                .build();

        RequestMonthlyBudget request = RequestMonthlyBudget.builder()
                .monthlyIncome(5000.0)
                .monthlyReference("07/2024")
                .itemBudgetList(Collections.singletonList(itemBudget))
                .build();

        when(categoryRepository.getAllCategoryByDocument(userDocument)).thenReturn(Collections.emptyList());

        assertThrows(CategoryException.class, () -> postMonthlyBudgetGateway.postMonthlyBudget(request, userDocument));

        verify(repository, never()).save(any(DbMonthlyBudget.class));
        verify(itemRepository, never()).saveAll(anyList());
    }

    @Test
    public void testPostMonthlyBudget_NoCategoryItems() {
        String userDocument = "user123";
        RequestMonthlyBudget request = RequestMonthlyBudget.builder()
                .monthlyIncome(5000.0)
                .monthlyReference("07/2024")
                .itemBudgetList(Collections.emptyList())
                .build();

        when(categoryRepository.getAllCategoryByDocument(userDocument)).thenReturn(Collections.emptyList());

        assertThrows(MonthlyBudgetException.class, () -> postMonthlyBudgetGateway.postMonthlyBudget(request, userDocument));

        verify(repository, never()).save(any(DbMonthlyBudget.class));
        verify(itemRepository, never()).saveAll(anyList());
    }
}
