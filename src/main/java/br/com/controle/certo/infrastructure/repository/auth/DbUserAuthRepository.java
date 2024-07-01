package br.com.controle.certo.infrastructure.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DbUserAuthRepository extends JpaRepository<DbUserAuth, Integer> {
    @Query("SELECT u FROM DbUserAuth u WHERE u.username = :username AND u.dhExclude IS NULL")
    Optional<DbUserAuth> findByUsernameAndDhExcludeIsNull(@Param("username") String username);

    boolean existsByUsername(String username);

    @Query("""
            SELECT us FROM DbUserAuth us
            WHERE us.username = :username AND us.emailUser = :emailUser
            """)
    DbUserAuth findByUsernameAndEmail(@Param(value = "username") String username, @Param(value = "emailUser") String email);

    @Modifying
    @Query("""
            UPDATE DbUserAuth u
            SET u.dhExclude = CURRENT_TIMESTAMP
            WHERE u.username =:username
            """)
    void deleteUserAuthByUserDocument(@Param(value = "username") String username);
}