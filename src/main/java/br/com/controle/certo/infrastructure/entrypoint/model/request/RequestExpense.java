package br.com.controle.certo.infrastructure.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
@Builder
public class RequestExpense {
    @NotBlank(message = "A descrição da despesa não pode estar vazia")
    @JsonProperty("descricao_despesa")
    private String expenseDescription;

    @NotNull(message = "O valor da despesa não pode ser nulo")
    @Positive(message = "O valor da despesa deve ser positivo")
    @JsonProperty("valor_despesa")
    private Double expenseValue;

    @Min(value = 1, message = "A parcela atual deve ser pelo menos 1")
    @JsonProperty("parcela_atual")
    private Integer currentInstallment;

    @Min(value = 2, message = "O total de parcelas deve ser pelo menos 1")
    @JsonProperty("parcela_total")
    private Integer totalInstallment;

    @JsonProperty("despesa_recorrente")
    private Boolean recurringExpense;

    @NotNull(message = "O ID da categoria não pode ser nulo")
    @Positive(message = "O ID da categoria deve ser um número positivo")
    @JsonProperty("id_categoria")
    private Integer dbCategory;

}