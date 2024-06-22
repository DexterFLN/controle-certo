package br.com.controle.certo.infrastructure.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class RequestMonthlyBudget {
    @JsonProperty("renda_mensal")
    private Double monthlyIncome;
    @JsonProperty("mes_ano_referencia")
    private String monthlyReference;
    @JsonProperty("item_despesa")
    private List<RequestItemBudget> itemBudgetList;
}