package br.com.controle.certo.infrastructure.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseExpensive {
    @JsonProperty("nome_categoria")
    private String categoryName;
    @JsonProperty("descricao_categoria")
    private String categoryDescription;
}
