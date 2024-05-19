package br.com.controle.certo.infrastructure.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestCategory {
    @JsonProperty("nome_categoria")
    private String categoryName;
    @JsonProperty("descricao_categoria")
    private String categoryDescription;
}
