package br.com.controle.certo.domain.entities;

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
    private LocalDateTime dhExclude;
    private Double monthlyIncome;
    private String monthlyReference;
    private UserEntity userEntity;
    private List<ItemBudgetEntity> itemBudgetEntityList;
}
