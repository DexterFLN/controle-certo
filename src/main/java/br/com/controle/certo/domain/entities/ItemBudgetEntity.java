package br.com.controle.certo.domain.entities;

import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import br.com.controle.certo.infrastructure.repository.model.DbMonthlyBudget;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ItemBudgetEntity {
    private Integer idItemBudget;
    private Boolean budgetTarget;
    private Double expenseValue;
    private LocalDateTime dhCreate;
    private LocalDateTime dhUpdate;
    private DbCategory dbCategory;
    private DbMonthlyBudget dbMonthlyBudget;
}
