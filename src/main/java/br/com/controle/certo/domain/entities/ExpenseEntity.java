package br.com.controle.certo.domain.entities;

import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ExpenseEntity {
    private Integer idExpense;
    private String expenseDescription;
    private Double expenseValue;
    private Integer currentInstallment;
    private Integer recurringExpense;
    private LocalDateTime dhCreate;
    private LocalDateTime dhUpdate;
    private DbCategory dbCategory;
    private DbUser dbUser;
}
