package br.com.controle.certo.infrastructure.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tb_categoria")
public class DbCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategory;
    @Column(name = "nome_categoria")
    private String categoryName;
    @Column(name = "descricao_categoria")
    private String categoryDescription;
    @Column(name = "dh_criacao")
    private LocalDateTime dhCreate;
    @Column(name = "dh_atualizacao")
    private LocalDateTime dhUpdate;

    @PrePersist
    protected void prePersist() {
        dhCreate = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        dhUpdate = LocalDateTime.now();
    }
}
