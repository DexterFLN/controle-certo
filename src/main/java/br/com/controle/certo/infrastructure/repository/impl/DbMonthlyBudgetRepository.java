package br.com.controle.certo.infrastructure.repository.impl;

import br.com.controle.certo.infrastructure.repository.model.DbMonthlyBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DbMonthlyBudgetRepository extends JpaRepository<DbMonthlyBudget, Integer> {

    @Query("""
            SELECT b FROM DbMonthlyBudget b
            JOIN b.dbUser u
            WHERE u.documentNumber = :userDocument
            AND b.dhCreate = (SELECT MAX(b2.dhCreate) FROM DbMonthlyBudget b2
            JOIN b2.dbUser u2
                WHERE u2.documentNumber = :userDocument)
            """)
    DbMonthlyBudget findMostRecentMonthlyBudgetByDocumentNumber(@Param("userDocument") String userDocument);

    @Query("""
            SELECT mb FROM DbMonthlyBudget mb
            JOIN mb.dbUser us
            WHERE us.documentNumber = :userDocument
            AND mb.dhExclude IS NULL
            AND mb.idMonthlyBudget = :idMonthlyBudget
                """)
    DbMonthlyBudget getDbMonthlyBudgetByUserDocumentAndId(@Param(value = "userDocument") String userDocument,
                                                          @Param(value = "idMonthlyBudget") Integer idMonthlyBudget);

    @Query("""
            SELECT mb FROM DbMonthlyBudget mb
            JOIN mb.dbUser us
            WHERE us.documentNumber = :userDocument
            AND mb.dhExclude IS NULL
            AND mb.monthlyReference = :monthlyReference
                """)
    DbMonthlyBudget getDbMonthlyBudgetCurrentMonth(@Param(value = "userDocument") String userDocument,
                                                   @Param(value = "monthlyReference") String monthlyReference);

    @Transactional
    @Modifying
    @Query("""
            UPDATE DbMonthlyBudget mb
            SET mb.dhExclude = CURRENT_TIMESTAMP
            WHERE mb.idMonthlyBudget =:idMonthlyBudget
            """)
    void deleteMonthlyBudgetById(@Param(value = "idMonthlyBudget") Integer idMonthlyBudget);
}
