package br.com.controle.certo.application.usecase.user.impl;

import br.com.controle.certo.application.gateway.user.DeleteUserGateway;
import br.com.controle.certo.application.usecase.user.DeleteUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseImplTest {

    @Mock
    private DeleteUserGateway deleteUserGateway;

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    private Integer userId;

    @BeforeEach
    public void setUp() {
        userId = 1;
    }

    @Test
    public void testDeleteUserByUserDocument() {
        deleteUserUseCase.deleteUserByUserDocument(userId);

        verify(deleteUserGateway, times(1)).deleteUserByUserDocument(userId);
    }
}
