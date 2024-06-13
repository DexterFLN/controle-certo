package br.com.controle.certo.infrastructure.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ResponseExpensive {

    @JsonProperty("id_despesa")
    private Integer idExpense;
    @JsonProperty("uuid_despesa")
    private String uuidDespesa;
    @JsonProperty("despesa_descriacao")
    private String expenseDescription;
    @JsonProperty("despesa_valor")
    private Double expenseValue;
    @JsonProperty("parcela_atual")
    private Integer currentInstallment;
    @JsonProperty("parcela_total")
    private Integer totalInstallment;
    @JsonProperty("despesa_recorrente")
    private Boolean recurringExpense;
    @JsonProperty("despesa_dh_criacao")
    private LocalDateTime dhCreate;
    @JsonProperty("despesa_dh_atualizacao")
    private LocalDateTime dhUpdate;
    @JsonProperty("categoria_despesa")
    private ResponseCategory responseCategory;
}
