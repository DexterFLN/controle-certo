package br.com.controle.certo.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CategoryEntity {
    private Integer idCategory;
    private String categoryName;
    private String categoryDescription;
    private LocalDateTime dhCreate;
    private LocalDateTime dhUpdate;
}
