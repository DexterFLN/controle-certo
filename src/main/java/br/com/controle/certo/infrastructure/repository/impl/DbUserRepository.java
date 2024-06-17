package br.com.controle.certo.infrastructure.repository.impl;

import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DbUserRepository extends JpaRepository<DbUser, Integer> {

    @Query("""
            SELECT u FROM DbUser u
            WHERE u.documentNumber = :document
            """)
    DbUser getUserByDocument(@Param(value = "document") String document);

    @Query("SELECT u FROM DbUser u")
    List<DbUser> getAllUsers();

    @Transactional
    @Modifying
    @Query("""
            UPDATE DbUser u
            SET u.dhExclude = CURRENT_TIMESTAMP
            WHERE u.documentNumber =:userDocument
            """)
    void deleteUserByDocument(@Param(value = "userDocument") String userDocument);
}