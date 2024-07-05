package br.com.controle.certo.application.usecase.user.impl;

import br.com.controle.certo.application.gateway.user.GetUserGateway;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetUserAllUseCaseImplTest {

    @Mock
    private GetUserGateway getUserGateway;

    @InjectMocks
    private GetUserAllUseCaseImpl getUserAllUseCase;

    private UserEntity userEntity1;
    private UserEntity userEntity2;

    @BeforeEach
    public void setUp() {
        userEntity1 = UserEntity.builder()
                .idUser(1)
                .userName("Test User 1")
                .documentNumber("12345678901")
                .emailUser("test1@example.com")
                .phoneNumber("1234567890")
                .uf("SP")
                .cep("12345-678")
                .neighborhood("Neighborhood 1")
                .county("County 1")
                .acceptTerms(true)
                .lvlUser(1)
                .xpUser(100)
                .build();

        userEntity2 = UserEntity.builder()
                .idUser(2)
                .userName("Test User 2")
                .documentNumber("12345678902")
                .emailUser("test2@example.com")
                .phoneNumber("0987654321")
                .uf("RJ")
                .cep("87654-321")
                .neighborhood("Neighborhood 2")
                .county("County 2")
                .acceptTerms(false)
                .lvlUser(2)
                .xpUser(200)
                .build();
    }

    @Test
    public void testGetUserAll() {
        List<UserEntity> userList = Arrays.asList(userEntity1, userEntity2);
        when(getUserGateway.getUserAll()).thenReturn(userList);

        List<ResponseUser> responseUserList = getUserAllUseCase.getUserAll();

        verify(getUserGateway, times(1)).getUserAll();
        assertNotNull(responseUserList);
        assertEquals(2, responseUserList.size());

        ResponseUser responseUser1 = responseUserList.get(0);
        assertEquals(userEntity1.getIdUser(), responseUser1.getIdUser());
        assertEquals(userEntity1.getUserName(), responseUser1.getUserName());
        assertEquals(userEntity1.getDocumentNumber(), responseUser1.getDocumentNumber());
        assertEquals(userEntity1.getEmailUser(), responseUser1.getEmailUser());
        assertEquals(userEntity1.getPhoneNumber(), responseUser1.getPhoneNumber());
        assertEquals(userEntity1.getUf(), responseUser1.getUf());
        assertEquals(userEntity1.getCep(), responseUser1.getCep());
        assertEquals(userEntity1.getNeighborhood(), responseUser1.getNeighborhood());
        assertEquals(userEntity1.getCounty(), responseUser1.getCounty());
        assertEquals(userEntity1.getAcceptTerms(), responseUser1.getAcceptTerms());
        assertEquals(userEntity1.getLvlUser(), responseUser1.getLvlUser());
        assertEquals(userEntity1.getXpUser(), responseUser1.getXpUser());

        ResponseUser responseUser2 = responseUserList.get(1);
        assertEquals(userEntity2.getIdUser(), responseUser2.getIdUser());
        assertEquals(userEntity2.getUserName(), responseUser2.getUserName());
        assertEquals(userEntity2.getDocumentNumber(), responseUser2.getDocumentNumber());
        assertEquals(userEntity2.getEmailUser(), responseUser2.getEmailUser());
        assertEquals(userEntity2.getPhoneNumber(), responseUser2.getPhoneNumber());
        assertEquals(userEntity2.getUf(), responseUser2.getUf());
        assertEquals(userEntity2.getCep(), responseUser2.getCep());
        assertEquals(userEntity2.getNeighborhood(), responseUser2.getNeighborhood());
        assertEquals(userEntity2.getCounty(), responseUser2.getCounty());
        assertEquals(userEntity2.getAcceptTerms(), responseUser2.getAcceptTerms());
        assertEquals(userEntity2.getLvlUser(), responseUser2.getLvlUser());
        assertEquals(userEntity2.getXpUser(), responseUser2.getXpUser());
    }
}
