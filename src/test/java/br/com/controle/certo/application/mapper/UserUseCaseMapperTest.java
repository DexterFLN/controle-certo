package br.com.controle.certo.application.mapper;

import br.com.controle.certo.domain.entities.UserEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserUseCaseMapperTest {

    @Test
    public void testUserEntityToResponseUser() {
        UserEntity userEntity = UserEntity.builder()
                .idUser(1)
                .userName("John Doe")
                .documentNumber("12345678900")
                .emailUser("john.doe@example.com")
                .phoneNumber("123456789")
                .uf("SP")
                .cep("12345678")
                .neighborhood("Neighborhood")
                .county("County")
                .acceptTerms(true)
                .lvlUser(5)
                .xpUser(100)
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .build();

        ResponseUser response = UserUseCaseMapper.userEntityToResponseUser(userEntity);

        assertNotNull(response);
        assertEquals(userEntity.getIdUser(), response.getIdUser());
        assertEquals(userEntity.getUserName(), response.getUserName());
        assertEquals(userEntity.getDocumentNumber(), response.getDocumentNumber());
        assertEquals(userEntity.getEmailUser(), response.getEmailUser());
        assertEquals(userEntity.getPhoneNumber(), response.getPhoneNumber());
        assertEquals(userEntity.getUf(), response.getUf());
        assertEquals(userEntity.getCep(), response.getCep());
        assertEquals(userEntity.getNeighborhood(), response.getNeighborhood());
        assertEquals(userEntity.getCounty(), response.getCounty());
        assertEquals(userEntity.getAcceptTerms(), response.getAcceptTerms());
        assertEquals(userEntity.getLvlUser(), response.getLvlUser());
        assertEquals(userEntity.getXpUser(), response.getXpUser());
        assertEquals(userEntity.getDhCreate(), response.getDhCreate());
        assertEquals(userEntity.getDhUpdate(), response.getDhUpdate());
    }

    @Test
    public void testUserEntityToResponseUser_NullInput() {
        ResponseUser response = UserUseCaseMapper.userEntityToResponseUser((UserEntity) null);
        assertNull(response);
    }

    @Test
    public void testUserEntityToResponseUser_List() {
        UserEntity userEntity1 = UserEntity.builder()
                .idUser(1)
                .userName("John Doe")
                .documentNumber("12345678900")
                .emailUser("john.doe@example.com")
                .phoneNumber("123456789")
                .uf("SP")
                .cep("12345678")
                .neighborhood("Neighborhood")
                .county("County")
                .acceptTerms(true)
                .lvlUser(5)
                .xpUser(100)
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .build();

        UserEntity userEntity2 = UserEntity.builder()
                .idUser(2)
                .userName("Jane Doe")
                .documentNumber("98765432100")
                .emailUser("jane.doe@example.com")
                .phoneNumber("987654321")
                .uf("RJ")
                .cep("87654321")
                .neighborhood("Another Neighborhood")
                .county("Another County")
                .acceptTerms(false)
                .lvlUser(10)
                .xpUser(200)
                .dhCreate(LocalDateTime.now())
                .dhUpdate(LocalDateTime.now())
                .build();

        List<UserEntity> userEntities = Arrays.asList(userEntity1, userEntity2);

        List<ResponseUser> responseList = UserUseCaseMapper.userEntityToResponseUser(userEntities);

        assertNotNull(responseList);
        assertEquals(2, responseList.size());

        ResponseUser response1 = responseList.get(0);
        assertEquals(userEntity1.getIdUser(), response1.getIdUser());
        assertEquals(userEntity1.getUserName(), response1.getUserName());
        assertEquals(userEntity1.getDocumentNumber(), response1.getDocumentNumber());
        assertEquals(userEntity1.getEmailUser(), response1.getEmailUser());
        assertEquals(userEntity1.getPhoneNumber(), response1.getPhoneNumber());
        assertEquals(userEntity1.getUf(), response1.getUf());
        assertEquals(userEntity1.getCep(), response1.getCep());
        assertEquals(userEntity1.getNeighborhood(), response1.getNeighborhood());
        assertEquals(userEntity1.getCounty(), response1.getCounty());
        assertEquals(userEntity1.getAcceptTerms(), response1.getAcceptTerms());
        assertEquals(userEntity1.getLvlUser(), response1.getLvlUser());
        assertEquals(userEntity1.getXpUser(), response1.getXpUser());
        assertEquals(userEntity1.getDhCreate(), response1.getDhCreate());
        assertEquals(userEntity1.getDhUpdate(), response1.getDhUpdate());

        ResponseUser response2 = responseList.get(1);
        assertEquals(userEntity2.getIdUser(), response2.getIdUser());
        assertEquals(userEntity2.getUserName(), response2.getUserName());
        assertEquals(userEntity2.getDocumentNumber(), response2.getDocumentNumber());
        assertEquals(userEntity2.getEmailUser(), response2.getEmailUser());
        assertEquals(userEntity2.getPhoneNumber(), response2.getPhoneNumber());
        assertEquals(userEntity2.getUf(), response2.getUf());
        assertEquals(userEntity2.getCep(), response2.getCep());
        assertEquals(userEntity2.getNeighborhood(), response2.getNeighborhood());
        assertEquals(userEntity2.getCounty(), response2.getCounty());
        assertEquals(userEntity2.getAcceptTerms(), response2.getAcceptTerms());
        assertEquals(userEntity2.getLvlUser(), response2.getLvlUser());
        assertEquals(userEntity2.getXpUser(), response2.getXpUser());
        assertEquals(userEntity2.getDhCreate(), response2.getDhCreate());
        assertEquals(userEntity2.getDhUpdate(), response2.getDhUpdate());
    }

    @Test
    public void testUserEntityToResponseUser_ListNullInput() {
        List<ResponseUser> responseList = UserUseCaseMapper.userEntityToResponseUser((List<UserEntity>) null);
        assertNotNull(responseList);
        assertEquals(0, responseList.size());
    }
}
