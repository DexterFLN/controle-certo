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
@Table(name = "tb_despesa")
public class DbExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_despesa")
    private Integer idExpense;
    @Column(name = "descricao_despesa")
    private String expenseDescription;
    @Column(name = "valor_despesa")
    private Double expenseValue;
    @Column(name = "parcela_atual")
    private Integer currentInstallment;
    @Column(name = "despesa_recorrente")
    private Integer recurringExpense;
    @Column(name = "dh_criacao")
    private LocalDateTime dhCreate;
    @Column(name = "dh_atualizacao")
    private LocalDateTime dhUpdate;
    @OneToOne
    @JoinColumn(name = "id_categoria")
    private DbCategory dbCategory;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private DbUser dbUser;

    @PrePersist
    protected void prePersist() {
        dhCreate = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        dhUpdate = LocalDateTime.now();
    }
}
