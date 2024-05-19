package br.com.controle.certo.infrastructure.repository.impl;


import br.com.controle.certo.infrastructure.repository.model.DbItemBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbItemBudgetRepository extends JpaRepository<DbItemBudget, Integer> {
}
