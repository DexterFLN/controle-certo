package br.com.controle.certo.domain.entities;

import br.com.controle.certo.infrastructure.repository.model.DbExpense;
import br.com.controle.certo.infrastructure.repository.model.DbMonthlyBudget;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class UserEntity {
    private Integer idUser;
    private String userName;
    private String documentNumber;
    private String emailUser;
    private String phoneNumber;
    private String uf;
    private String cep;
    private String neighborhood;
    private String county;
    private Boolean acceptTerms;
    private Integer lvlUser;
    private Integer xpUser;
    private LocalDateTime dhCreate;
    private LocalDateTime dhUpdate;
    private LocalDateTime dhExclude;
    private List<DbExpense> dbExpenseList;
    private List<DbMonthlyBudget> dbMonthlyBudgetList;
}