package br.com.controle.certo.infrastructure.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbUserAuthPermissionRepository extends JpaRepository<DbUserAuth, Integer> {
}
