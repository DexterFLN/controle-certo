package br.com.controle.certo.infrastructure.gateway.user;

import br.com.controle.certo.infrastructure.repository.auth.DbUserAuthRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class DeleteUserGatewayImplTest {

    @InjectMocks
    private DeleteUserGatewayImpl deleteUserGateway;

    @Mock
    private DbUserRepository userRepository;

    @Mock
    private DbUserAuthRepository authRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteUserByUserDocument_Success() {
        Integer userId = 1;
        String documentNumber = "123456789";

        DbUser dbUser = DbUser.builder()
                .idUser(userId)
                .documentNumber(documentNumber)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(dbUser));

        deleteUserGateway.deleteUserByUserDocument(userId);

        verify(userRepository, times(1)).deleteUserById(userId);
        verify(authRepository, times(1)).deleteUserAuthByUserDocument(documentNumber);
    }

}
