package br.com.controle.certo.application.usecase.user.impl;

import br.com.controle.certo.application.gateway.user.PostUserGateway;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUser;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostUserUseCaseImplTest {

    @Mock
    private PostUserGateway postUserGateway;

    @InjectMocks
    private PostUserUseCaseImpl postUserUseCase;

    private UserEntity userEntity;
    private RequestUser requestUser;
    private String userDocument;

    @BeforeEach
    public void setUp() {
        userDocument = "123456789";

        userEntity = UserEntity.builder()
                .idUser(1)
                .userName("Test User")
                .documentNumber(userDocument)
                .emailUser("test@example.com")
                .phoneNumber("123456789")
                .uf("SP")
                .cep("12345-678")
                .neighborhood("Test Neighborhood")
                .county("Test County")
                .acceptTerms(true)
                .lvlUser(1)
                .xpUser(100)
                .build();

        requestUser = RequestUser.builder()
                .documentNumber(userDocument)
                .emailUser("test@example.com")
                .phoneNumber("123456789")
                .uf("SP")
                .county("Test County")
                .acceptTerms(true)
                .build();
    }

    @Test
    public void testPostUser() {
        when(postUserGateway.postUser(requestUser, userDocument)).thenReturn(userEntity);

        ResponseUser responseUser = postUserUseCase.postUser(requestUser, userDocument);

        verify(postUserGateway, times(1)).postUser(requestUser, userDocument);
        assertEquals(userEntity.getIdUser(), responseUser.getIdUser());
        assertEquals(userEntity.getUserName(), responseUser.getUserName());
        assertEquals(userEntity.getDocumentNumber(), responseUser.getDocumentNumber());
        assertEquals(userEntity.getEmailUser(), responseUser.getEmailUser());
        assertEquals(userEntity.getPhoneNumber(), responseUser.getPhoneNumber());
        assertEquals(userEntity.getUf(), responseUser.getUf());
        assertEquals(userEntity.getCep(), responseUser.getCep());
        assertEquals(userEntity.getNeighborhood(), responseUser.getNeighborhood());
        assertEquals(userEntity.getCounty(), responseUser.getCounty());
        assertEquals(userEntity.getAcceptTerms(), responseUser.getAcceptTerms());
        assertEquals(userEntity.getLvlUser(), responseUser.getLvlUser());
        assertEquals(userEntity.getXpUser(), responseUser.getXpUser());
    }
}
