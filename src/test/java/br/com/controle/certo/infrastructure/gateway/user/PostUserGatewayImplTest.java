package br.com.controle.certo.infrastructure.gateway.user;

import br.com.controle.certo.application.gateway.user.PostUserGateway;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUser;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PostUserGatewayImplTest {

    @Mock
    private DbUserRepository repository;

    @InjectMocks
    private PostUserGatewayImpl postUserGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPostUser() {
        RequestUser requestUser = RequestUser.builder()
                .documentNumber("123456789")
                .emailUser("john.doe@example.com")
                .phoneNumber("987654321")
                .uf("SP")
                .county("Cityville")
                .acceptTerms(true)
                .build();

        String userDocument = "123456789"; // Documento do usuário

        DbUser savedDbUser = DbUser.builder()
                .idUser(1)
                .documentNumber("123456789")
                .emailUser("john.doe@example.com")
                .phoneNumber("987654321")
                .uf("SP")
                .county("Cityville")
                .acceptTerms(true)
                .dhCreate(LocalDateTime.now())
                .build();

        when(repository.save(any(DbUser.class))).thenReturn(savedDbUser);

        UserEntity result = postUserGateway.postUser(requestUser, userDocument);

        verify(repository, times(1)).save(any(DbUser.class));

        assertEquals(savedDbUser.getDocumentNumber(), result.getDocumentNumber());
        assertEquals(savedDbUser.getEmailUser(), result.getEmailUser());
        assertEquals(savedDbUser.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(savedDbUser.getUf(), result.getUf());
        assertEquals(savedDbUser.getCounty(), result.getCounty());
        assertEquals(savedDbUser.getAcceptTerms(), result.getAcceptTerms());
    }
}
