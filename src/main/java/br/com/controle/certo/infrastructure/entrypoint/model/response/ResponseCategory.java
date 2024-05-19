package br.com.controle.certo.infrastructure.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ResponseCategory {
    @JsonProperty("id_categoria")
    private Integer idCategory;
    @JsonProperty("nome_categoria")
    private String categoryName;
    @JsonProperty("descricao_categoria")
    private String categoryDescription;
    @JsonProperty("categoria_dh_criacao")
    private LocalDateTime dhCreate;
    @JsonProperty("categoria_dh_update")
    private LocalDateTime dhUpdate;
}
