package br.com.controle.certo.infrastructure.repository.impl;

import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface DbCategoryRepository extends JpaRepository<DbCategory, Integer> {
    @Query("SELECT ct FROM DbCategory ct " +
            "INNER JOIN DbUserCategory uc ON uc.dbUserCategoryId.dbCategory = ct " +
            "INNER JOIN DbUser us ON uc.dbUserCategoryId.dbUser = us " +
            "WHERE us.documentNumber = :document")
    List<DbCategory> getAllCategoryByDocument(@Param(value = "document") String document);

    @Query("SELECT ct FROM DbCategory ct " +
            "INNER JOIN DbUserCategory uc ON uc.dbUserCategoryId.dbCategory = ct " +
            "INNER JOIN DbUser us ON uc.dbUserCategoryId.dbUser = us " +
            "WHERE us.documentNumber = :document AND ct.idCategory = :idCategory")
    DbCategory getAllCategoryById(@Param(value = "document") String document, @Param(value = "idCategory") Integer idCategory);

    @Transactional
    @Modifying
    @Query("UPDATE DbCategory ct SET " +
            "ct.categoryDescription = :categoryDescription, " +
            "ct.categoryName = :categoryName " +
            "WHERE ct.idCategory = :idCategory")
    void updateCategoryById(@Param(value = "categoryName") String categoryName,
                                  @Param(value = "categoryDescription") String categoryDescription,
                                  @Param(value = "idCategory") Integer idCategory);

}
