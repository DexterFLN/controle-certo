package br.com.controle.certo.infrastructure.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestItemBudget {

    @JsonProperty("valor_despesa_categoria")
    private Double expenseValue;
    @JsonProperty("id_categoria")
    private Integer dbCategory;
}
