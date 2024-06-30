package br.com.controle.certo.infrastructure.entrypoint.model.response;

import br.com.controle.certo.domain.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMonthlyBudget {
    @JsonProperty("id_orcamento")
    private Integer idMonthlyBudget;
    @JsonProperty("orcamento_dh_criacao")
    private LocalDateTime dhCreate;
    @JsonProperty("orcamento_dh_atualizacao")
    private LocalDateTime dhUpdate;
    @JsonProperty("orcamento_dh_exclude")
    private LocalDateTime dhExclude;
    @JsonProperty("renda_mensal")
    private Double monthlyIncome;
    @JsonProperty("mes_ano_referente")
    private String monthlyReference;
    @JsonProperty("orcamento_itens_despesas")
    private List<ResponseItemBudget> itemBudgetEntityList;
}
