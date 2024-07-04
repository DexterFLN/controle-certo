package br.com.controle.certo.infrastructure.repository.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_item_orcamento")
public class DbItemBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_orcamento")
    private Integer idItemBudget;
    @Column(name = "meta_orcamento")
    private Boolean budgetTarget;
    @Column(name = "valor_despesa_categoria")
    private Double expenseValue;
    @Column(name = "dh_criacao")
    private LocalDateTime dhCreate;
    @Column(name = "dh_atualizacao")
    private LocalDateTime dhUpdate;
    @Column(name = "experiencia_concedida")
    private Boolean experienceGranted = false;
    @OneToOne
    @JoinColumn(name = "id_categoria")
    private DbCategory dbCategory;
    @ManyToOne
    @JoinColumn(name = "id_orcamento_mensal")
    private DbMonthlyBudget dbMonthlyBudget;

    @PrePersist
    protected void prePersist() {
        dhCreate = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        dhUpdate = LocalDateTime.now();
    }
}
