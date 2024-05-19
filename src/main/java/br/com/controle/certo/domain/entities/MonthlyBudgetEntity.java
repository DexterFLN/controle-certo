package br.com.controle.certo.domain.entities;

import br.com.controle.certo.infrastructure.repository.model.DbItemBudget;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class MonthlyBudgetEntity {
    private Integer idMonthlyBudget;
    private LocalDateTime dhCreate;
    private LocalDateTime dhUpdate;
    private DbUser dbUser;
    private List<DbItemBudget> dbItemBudget;
}
