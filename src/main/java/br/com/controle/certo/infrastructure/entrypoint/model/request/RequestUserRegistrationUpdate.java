package br.com.controle.certo.infrastructure.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestUserRegistrationUpdate {
    @JsonProperty("new_password")
    private String newPassword;
    @JsonProperty("old_password")
    private String oldPassword;
}
