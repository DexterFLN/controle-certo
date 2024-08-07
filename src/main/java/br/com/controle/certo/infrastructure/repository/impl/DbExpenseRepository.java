package br.com.controle.certo.infrastructure.repository.impl;

import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import br.com.controle.certo.infrastructure.repository.model.DbExpense;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DbExpenseRepository extends JpaRepository<DbExpense, Integer> {

    @Query("""
            SELECT e FROM DbExpense e
            WHERE e.dbUser = :user
            AND e.dhExclude IS NULL
            AND e.dhCreate >= :startDate
            AND e.dhCreate < :endDate
            """)
    List<DbExpense> findByDbUserAndMonth(@Param("user") DbUser user,
                                         @Param("startDate") LocalDateTime startDate,
                                         @Param("endDate") LocalDateTime endDate);
    @Query("""
            SELECT ex FROM DbExpense ex
            INNER JOIN ex.dbUser cd
            WHERE cd.documentNumber = :userDocument
            """)
    List<DbExpense> getAllExpenseByUserDocument(@Param(value = "userDocument") String userDocument);

    @Query("""
            SELECT ex FROM DbExpense ex
            INNER JOIN ex.dbUser cd
            WHERE cd.documentNumber = :userDocument AND ex.idExpense = :id
            """)
    DbExpense getExpenseByIdExpenseAndUserDocument(@Param(value = "id") Integer idExpense,
                                                   @Param(value = "userDocument") String userDocument);

    @Query("""
            SELECT ex FROM DbExpense ex
            INNER JOIN ex.dbUser cd
            WHERE cd.documentNumber = :userDocument
            AND ex.dhExclude IS NULL
            AND MONTH(ex.dhCreate) = :month
            AND YEAR(ex.dhCreate) = :year
            """)
    List<DbExpense> getExpenseCurrentMonthByUserDocument(@Param(value = "userDocument") String userDocument,
                                                         @Param(value = "month") int month,
                                                         @Param(value = "year") int year);

    @Transactional
    @Modifying
    @Query("""
            UPDATE DbExpense ex SET
            ex.dhExclude = CURRENT_TIMESTAMP
            WHERE ex.idExpense = :idExpense
            AND ex.dhExclude IS NULL
            AND MONTH(ex.dhCreate) = :month
            AND YEAR(ex.dhCreate) = :year
            """)
    void deleteExpenseById(@Param(value = "idExpense") Integer idCategory,
                           @Param(value = "month") int month,
                           @Param(value = "year") int year);

    @Transactional
    @Modifying
    @Query("""
        UPDATE DbExpense ex SET
        ex.dhExclude = CURRENT_TIMESTAMP
        WHERE ex.uuidExpense = :uuidExpense
        AND ex.dhExclude IS NULL
        AND DATE_TRUNC('month', ex.dhCreate) >= DATE_TRUNC('month', TO_DATE(:year || '-' || :month, 'YYYY-MM'))
        """)
    void deleteExpenseByUuid(@Param(value = "uuidExpense") String uuidExpense,
                             @Param(value = "month") int month,
                             @Param(value = "year") int year);

    @Transactional
    @Modifying
    @Query("""
            UPDATE DbExpense ex SET
            ex.expenseDescription = :expenseDescription,
            ex.expenseValue = :expenseValue,
            ex.dbCategory = :dbCategory
            WHERE ex.uuidExpense = :uuidExpense
            AND ex.dhCreate >= :dhCreate
            """)
    void updateExpenseByUuid(@Param(value = "uuidExpense") String uuidExpense,
                             @Param(value = "expenseDescription") String expenseDescription,
                             @Param(value = "expenseValue") Double expenseValue,
                             @Param(value = "dbCategory") DbCategory dbCategory,
                             @Param(value = "dhCreate") LocalDateTime dhCreate);

    @Transactional
    @Modifying
    @Query("""
            UPDATE DbExpense ex SET
            ex.expenseDescription = :expenseDescription,
            ex.expenseValue = :expenseValue,
            ex.dbCategory = :dbCategory
            WHERE ex.idExpense = :idExpense
            AND ex.dhCreate >= :dhCreate
            """)
    void updateExpenseById(@Param(value = "idExpense") Integer idExpense,
                           @Param(value = "expenseDescription") String expenseDescription,
                           @Param(value = "expenseValue") Double expenseValue,
                           @Param(value = "dbCategory") DbCategory dbCategory,
                           @Param(value = "dhCreate") LocalDateTime dhCreate);

}
