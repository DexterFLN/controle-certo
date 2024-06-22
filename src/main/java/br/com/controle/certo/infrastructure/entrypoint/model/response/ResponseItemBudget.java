package br.com.controle.certo.infrastructure.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseItemBudget {
    @JsonProperty("id_item_despesa")
    private Integer idItemBudget;
    @JsonProperty("item_despesa_meta_orcamento")
    private Boolean budgetTarget;
    @JsonProperty("item_despesa_percentual")
    private Double categoryPercentage;
    @JsonProperty("item_despesa_dh_criacao")
    private LocalDateTime dhCreate;
    @JsonProperty("item_despesa_dh_atualizacao")
    private LocalDateTime dhUpdate;
    @JsonProperty("item_despesa_categoria")
    private ResponseCategory dbCategory;
}