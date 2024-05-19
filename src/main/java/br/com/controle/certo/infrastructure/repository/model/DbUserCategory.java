package br.com.controle.certo.infrastructure.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tb_usuario_categoria")
public class DbUserCategory {
    @EmbeddedId
    private DbUserCategoryId dbUserCategoryId;
}
