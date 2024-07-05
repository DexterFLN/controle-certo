package br.com.controle.certo.application.usecase.user.impl;

import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.application.gateway.user.PutUserGateway;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.handler.UserException;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestPutUser;
import br.com.controle.certo.infrastructure.repository.auth.DbUserAuth;
import br.com.controle.certo.infrastructure.repository.auth.DbUserAuthRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PutUserUseCaseImplTest {

    @Mock
    private GetUserGateway getUserGateway;

    @Mock
    private PutUserGateway putUserGateway;

    @Mock
    private DbUserAuthRepository userAuthRepository;

    @InjectMocks
    private PutUserUseCaseImpl putUserUseCase;

    private UserEntity userEntity;
    private DbUserAuth dbUserAuth;

    @BeforeEach
    public void setUp() {
        userEntity = UserEntity.builder()
                .idUser(1)
                .documentNumber("123456789")
                .userName("Test User")
                .emailUser("test@example.com")
                .phoneNumber("123456789")
                .cep("12345-678")
                .uf("SP")
                .county("Test County")
                .neighborhood("Test Neighborhood")
                .build();

        dbUserAuth = DbUserAuth.builder()
                .idUser(1)
                .username("test_user")
                .emailUser("test@example.com")
                .build();
    }

    @Test
    public void testPutUserByUserDocument_UserNotFound() {
        when(getUserGateway.getUserByUserDocument("123456789")).thenReturn(null);

        RequestPutUser requestPutUser = RequestPutUser.builder().build();

        assertThrows(UserException.class, () -> putUserUseCase.putUserByUserDocument(requestPutUser, "123456789"));
    }

    @Test
    public void testPutUserByUserDocument_UpdateEmail() {
        when(getUserGateway.getUserByUserDocument("123456789")).thenReturn(userEntity);
        when(userAuthRepository.findByUsernameAndDhExcludeIsNull("123456789")).thenReturn(Optional.of(dbUserAuth));

        RequestPutUser requestPutUser = RequestPutUser.builder()
                .emailUser("new_email@example.com")
                .build();

        putUserUseCase.putUserByUserDocument(requestPutUser, "123456789");

        verify(userAuthRepository).save(dbUserAuth);
        verify(putUserGateway).putUserByUserDocument(userEntity);
        assertEquals("new_email@example.com", dbUserAuth.getEmailUser());
        assertEquals("new_email@example.com", userEntity.getEmailUser());
    }

    @Test
    public void testPutUserByUserDocument_UpdateOtherFields() {
        when(getUserGateway.getUserByUserDocument("123456789")).thenReturn(userEntity);

        RequestPutUser requestPutUser = RequestPutUser.builder()
                .userName("New Name")
                .phoneNumber("987654321")
                .cep("87654-321")
                .uf("RJ")
                .county("New County")
                .neighborhood("New Neighborhood")
                .build();

        putUserUseCase.putUserByUserDocument(requestPutUser, "123456789");

        verify(putUserGateway).putUserByUserDocument(userEntity);
        assertEquals("New Name", userEntity.getUserName());
        assertEquals("987654321", userEntity.getPhoneNumber());
        assertEquals("87654-321", userEntity.getCep());
        assertEquals("RJ", userEntity.getUf());
        assertEquals("New County", userEntity.getCounty());
        assertEquals("New Neighborhood", userEntity.getNeighborhood());
    }
}
