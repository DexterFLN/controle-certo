package br.com.controle.certo.infrastructure.mappers;

import br.com.controle.certo.domain.entities.UserCategoryEntity;
import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import br.com.controle.certo.infrastructure.repository.model.DbUserCategory;
import br.com.controle.certo.infrastructure.repository.model.DbUserCategoryId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserCategoryMapperTest {

    @Test
    public void testDbUserCategoryToUserCategoryEntity() {
        DbUser dbUser = new DbUser();
        dbUser.setIdUser(1);
        dbUser.setUserName("Test User");

        DbCategory dbCategory = new DbCategory();
        dbCategory.setIdCategory(1);
        dbCategory.setCategoryName("Test Category");
        dbCategory.setCategoryDescription("Test Description");

        DbUserCategoryId dbUserCategoryId = new DbUserCategoryId();
        dbUserCategoryId.setDbUser(dbUser);
        dbUserCategoryId.setDbCategory(dbCategory);

        DbUserCategory dbUserCategory = new DbUserCategory();
        dbUserCategory.setDbUserCategoryId(dbUserCategoryId);

        UserCategoryEntity userCategoryEntity = UserCategoryMapper.dbUserCategoryToUserCategoryEntity(dbUserCategory);

        assertNotNull(userCategoryEntity);
        assertNotNull(userCategoryEntity.getDbUser());
        assertNotNull(userCategoryEntity.getDbCategory());
        assertEquals(dbUser.getIdUser(), userCategoryEntity.getDbUser().getIdUser());
        assertEquals(dbUser.getUserName(), userCategoryEntity.getDbUser().getUserName());
        assertEquals(dbCategory.getIdCategory(), userCategoryEntity.getDbCategory().getIdCategory());
        assertEquals(dbCategory.getCategoryName(), userCategoryEntity.getDbCategory().getCategoryName());
        assertEquals(dbCategory.getCategoryDescription(), userCategoryEntity.getDbCategory().getCategoryDescription());
    }
}
