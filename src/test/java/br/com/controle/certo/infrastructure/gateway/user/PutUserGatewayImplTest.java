package br.com.controle.certo.infrastructure.gateway.user;

import br.com.controle.certo.application.gateway.user.PutUserGateway;
import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class PutUserGatewayImplTest {

    @Mock
    private DbUserRepository repository;

    @InjectMocks
    private PutUserGatewayImpl putUserGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPutUserByUserDocument() {
        UserEntity userEntity = UserEntity.builder()
                .idUser(1)
                .userName("John Doe")
                .documentNumber("123456789")
                .emailUser("john.doe@example.com")
                .phoneNumber("987654321")
                .uf("SP")
                .cep("12345-678")
                .neighborhood("Downtown")
                .county("Cityville")
                .acceptTerms(true)
                .lvlUser(1)
                .xpUser(100)
                .dhCreate(LocalDateTime.now())
                .build();

        putUserGateway.putUserByUserDocument(userEntity);

        verify(repository, times(1)).save(any(DbUser.class));
    }
}
