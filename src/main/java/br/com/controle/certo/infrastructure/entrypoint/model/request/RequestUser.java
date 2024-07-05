package br.com.controle.certo.infrastructure.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
public class RequestUser {
    @NotBlank(message = "O nome não pode estar vazio")
    @Size(min = 2, max = 30, message = "O nome deve ter entre 2 e 30 caracteres")
    @JsonProperty("nome_usuario")
    private String userName;

    @NotBlank(message = "O email não pode estar vazio")
    @Email(message = "Email deve ser válido")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Email deve seguir o formato padrão"
    )
    @JsonProperty("email_usuario")
    private String emailUser;

    @NotBlank(message = "O número de telefone não pode estar vazio")
    @NotBlank(message = "O número de telefone não pode estar vazio")
//    @Pattern(
//            regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$|^\\d{11}$",
//            message = "Número de telefone deve estar no formato (11) 12345-6789 ou 11987654321"
//    )
    @JsonProperty("numero_telefone")
    private String phoneNumber;

    @NotBlank(message = "A UF não pode estar vazia")
    @Size(min = 2, max = 2, message = "A UF deve ter exatamente 2 caracteres")
    @JsonProperty("uf")
    private String uf;

    @NotBlank(message = "O município não pode estar vazio")
    @JsonProperty("municipio")
    private String county;
    @NotBlank(message = "O bairro não pode estar vazio")
    @JsonProperty("bairro")
    private String neighborhood;

    @NotBlank(message = "O cep não pode estar vazio")
    @JsonProperty("cep")
    private String cep;
    @NotNull(message = "É necessário aceitar os termos")
    @JsonProperty("data_aceite_termos")
    private Boolean acceptTerms;
}
