package br.com.controle.certo.infrastructure.mappers;

import br.com.controle.certo.domain.entities.UserCategoryEntity;
import br.com.controle.certo.infrastructure.repository.model.DbUserCategory;

public class UserCategoryMapper {

    public static UserCategoryEntity dbUserCategoryToUserCategoryEntity(DbUserCategory body) {
        return UserCategoryEntity.builder()
                .dbCategory(body.getDbUserCategoryId().getDbCategory())
                .dbUser(body.getDbUserCategoryId().getDbUser())
                .build();
    }
}
