package br.com.controle.certo.infrastructure.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCategory {
    @JsonProperty("id_categoria")
    private Integer idCategory;
    @JsonProperty("categoria_nome")
    private String categoryName;
    @JsonProperty("categoria_descricao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private String categoryDescription;
    @JsonProperty("categoria_dh_criacao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dhCreate;
    @JsonProperty("categoria_dh_update")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dhUpdate;
}
