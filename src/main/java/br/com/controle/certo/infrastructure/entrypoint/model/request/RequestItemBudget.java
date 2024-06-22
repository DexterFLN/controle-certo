package br.com.controle.certo.infrastructure.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestItemBudget {

    @JsonProperty("percentual_categoria")
    private Double categoryPercentage;
    @JsonProperty("id_categoria")
    private Integer dbCategory;
}