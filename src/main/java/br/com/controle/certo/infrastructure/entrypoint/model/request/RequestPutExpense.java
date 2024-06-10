package br.com.controle.certo.infrastructure.entrypoint.model.request;

import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class RequestPutExpense {
    @Positive(message = "O valor da despesa deve ser positivo")
    @JsonProperty("id_despesa")
    private Integer idDespesa;
    @JsonProperty("uuid_despesa")
    private String uuidDespesa;
    @Size(min = 4, max = 20, message = "O nome da categoria deve ter entre 4 e 20 caracteres.")
    @JsonProperty("despesa_descriacao")
    private String expenseDescription;
    @Positive(message = "O valor da despesa deve ser positivo")
    @JsonProperty("despesa_valor")
    private Double expenseValue;
    @JsonProperty("categoria_despesa")
    private ResponseCategory responseCategory;
}