package br.com.controle.certo.infrastructure.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class DbUserCategoryId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private DbUser dbUser;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private DbCategory dbCategory;
}
