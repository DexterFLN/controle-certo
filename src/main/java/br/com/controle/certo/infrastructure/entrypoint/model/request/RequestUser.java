package br.com.controle.certo.infrastructure.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestUser {
    @JsonProperty("nr_documento")
    private String documentNumber;
    @JsonProperty("email_usuario")
    private String emailUser;
    @JsonProperty("numero_telefone")
    private String phoneNumber;
    @JsonProperty("uf")
    private String uf;
    @JsonProperty("municipio")
    private String county;
    @JsonProperty("data_aceite_termos")
    private Boolean acceptTerms;
}
