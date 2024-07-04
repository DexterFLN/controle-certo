package br.com.controle.certo.infrastructure.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonProperty("despesa_descricao")
    private String expenseDescription;
    @JsonProperty("despesa_valor")
    private Double expenseValue;
    @JsonProperty("parcela_atual")
    private Integer currentInstallment;
    @JsonProperty("parcela_total")
    private Integer totalInstallment;
    @JsonProperty("tipo_despesa")
    private String expenseType;
    @JsonProperty("despesa_dh_criacao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")

    private LocalDateTime dhCreate;
    @JsonProperty("despesa_dh_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dhUpdate;
    @JsonProperty("categoria_despesa")
    private ResponseCategory responseCategory;
}
