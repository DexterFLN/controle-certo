package br.com.controle.certo.infrastructure.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbPasswordResetTokenRepository extends JpaRepository<DbPasswordResetToken, Long> {
    DbPasswordResetToken findByToken(String token);

    void deleteByToken(String token);
}