package br.com.controle.certo.infrastructure.repository.impl;

import br.com.controle.certo.infrastructure.repository.model.DbMonthlyBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DbMonthlyBudgetRepository extends JpaRepository<DbMonthlyBudget, Integer> {

    @Query("""
        SELECT mb FROM DbMonthlyBudget mb
        LEFT JOIN FETCH mb.dbUser us
        LEFT JOIN FETCH mb.dbItemBudget ib
        LEFT JOIN FETCH ib.dbCategory
        WHERE us.documentNumber = :userDocument AND mb.dhExclude IS NULL AND
        EXTRACT(MONTH FROM mb.dhCreate) = :month AND EXTRACT(YEAR FROM mb.dhCreate) = :year
       """)
    DbMonthlyBudget getDbMonthlyBudgetCurrentMonth(@Param(value = "userDocument") String userDocument,
                                                   @Param(value = "month") int month,
                                                   @Param(value = "year") int year);

    @Transactional
    @Modifying
    @Query("""
            UPDATE DbMonthlyBudget mb
            SET mb.dhExclude = CURRENT_TIMESTAMP
            WHERE mb.idMonthlyBudget =:idMonthlyBudget
            """)
    void deleteMonthlyBudgetById(@Param(value = "idMonthlyBudget") Integer idMonthlyBudget);
}