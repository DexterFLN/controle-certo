package br.com.controle.certo.infrastructure.repository.impl;

import br.com.controle.certo.infrastructure.repository.model.DbUserCategory;
import br.com.controle.certo.infrastructure.repository.model.DbUserCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DbUserCategoryRepository extends JpaRepository<DbUserCategory, DbUserCategoryId> {

    @Query("SELECT uc FROM DbCategory ct " +
            "INNER JOIN DbUserCategory uc ON uc.dbUserCategoryId.dbCategory = ct " +
            "INNER JOIN DbUser us ON uc.dbUserCategoryId.dbUser = us " +
            "WHERE us.documentNumber = :document AND ct.idCategory = :idCategory")
    DbUserCategory getUserCategoryByDocumentAndIdCategory(@Param(value = "document") String document, @Param(value = "idCategory") Integer idCategory);
}
