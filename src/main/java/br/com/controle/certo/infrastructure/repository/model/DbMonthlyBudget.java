package br.com.controle.certo.infrastructure.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tb_orcamento_mensal")
public class DbMonthlyBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMonthlyBudget;
    @Column(name = "dh_criacao")
    private LocalDateTime dhCreate;
    @Column(name = "dh_atualizacao")
    private LocalDateTime dhUpdate;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private DbUser dbUser;

    @OneToMany
    private List<DbItemBudget> dbItemBudget;

    @PrePersist
    protected void prePersist() {
        dhCreate = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        dhUpdate = LocalDateTime.now();
    }
}
