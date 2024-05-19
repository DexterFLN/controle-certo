package br.com.controle.certo.domain.entities;

import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCategoryEntity {
    private DbUser dbUser;
    private DbCategory dbCategory;
}