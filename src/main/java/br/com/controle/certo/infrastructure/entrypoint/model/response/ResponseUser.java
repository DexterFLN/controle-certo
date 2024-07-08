package br.com.controle.certo.infrastructure.entrypoint.model.response;

import br.com.controle.certo.domain.entities.MonthlyBudgetEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ResponseUser {

    @JsonProperty("id_usuario")
    private Integer idUser;
    @JsonProperty("usuario_nome")
    private String userName;
    @JsonProperty("usuario_documento")
    private String documentNumber;
    @JsonProperty("usuario_email")
    private String emailUser;
    @JsonProperty("usuario_telefone")
    private String phoneNumber;
    @JsonProperty("uf")
    private String uf;
    @JsonProperty("cep")
    private String cep;
    @JsonProperty("bairro")
    private String neighborhood;
    @JsonProperty("municipio")
    private String county;
    @JsonProperty("usuario_aceite_termos")
    private Boolean acceptTerms;
    @JsonProperty("usuario_level")
    private Integer lvlUser;
    @JsonProperty("usuario_experiencia")
    private Integer xpUser;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonProperty("usuario_dh_criacao")
    private LocalDateTime dhCreate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonProperty("usuario_dh_atualizacao")
    private LocalDateTime dhUpdate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonProperty("usuario_dh_exclusao")
    private LocalDateTime dhExclude;
    @JsonProperty("despesas_usuario")
    private List<ResponseExpensive> expensiveList;
    @JsonProperty("orcamentos_mensais")
    private List<MonthlyBudgetEntity> monthlyBudgetEntityList;
}
