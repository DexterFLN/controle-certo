package br.com.controle.certo.infrastructure.repository.impl;

import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DbUserRepository extends JpaRepository<DbUser, Integer> {

    @Query("SELECT u FROM DbUser u " +
            "WHERE u.documentNumber = :document")
    DbUser getUserByDocument(@Param(value = "document") String document);
}
