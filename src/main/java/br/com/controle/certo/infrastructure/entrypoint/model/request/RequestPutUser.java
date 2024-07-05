package br.com.controle.certo.infrastructure.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestPutUser {
    @JsonProperty("usuario_nome")
    private String userName;

    @JsonProperty("usuario_email")
    private String emailUser;

    @JsonProperty("usuario_telefone")
    private String phoneNumber;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("uf")
    private String uf;

    @JsonProperty("municipio")
    private String county;

    @JsonProperty("bairro")
    private String neighborhood;
}