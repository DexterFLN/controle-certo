package br.com.controle.certo.application.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ResponseDbCategory {
    private Integer idCategory;
    private String categoryName;
    private String categoryDescription;
    private LocalDateTime dhCreate;
    private LocalDateTime dhUpdate;
}
