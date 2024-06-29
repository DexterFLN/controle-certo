package br.com.controle.certo.infrastructure.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DbUserAuthRepository extends JpaRepository<DbUserAuth, Integer> {
    Optional<DbUserAuth> findByUsername(String username);
    boolean existsByUsername(String username);

    @Query("""
            SELECT us FROM DbUserAuth us
            WHERE us.username = :username AND us.emailUser = :emailUser
            """)
    DbUserAuth findByUsernameAndEmail(@Param(value = "username") String username, @Param(value = "emailUser") String email);
}
