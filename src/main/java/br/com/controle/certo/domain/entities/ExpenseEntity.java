package br.com.controle.certo.domain.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseEntity {
    private Integer idExpense;
    private String uuidExpense;
    private String expenseDescription;
    private Double expenseValue;
    private Integer currentInstallment;
    private Integer totalInstallment;
    private Boolean recurringExpense;
    private LocalDateTime dhCreate;
    private LocalDateTime dhUpdate;
    private CategoryEntity categoryEntity;
    private UserEntity userEntity;
}
