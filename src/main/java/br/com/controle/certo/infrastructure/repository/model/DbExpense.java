package br.com.controle.certo.infrastructure.repository.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_despesa")
public class DbExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_despesa")
    private Integer idExpense;
    @Column(name = "uuid_expense")
    private String uuidExpense;
    @Column(name = "descricao_despesa")
    private String expenseDescription;
    @Column(name = "valor_despesa")
    private Double expenseValue;
    @Column(name = "parcela_atual")
    private Integer currentInstallment;
    @Column(name = "parcela_total")
    private Integer totalInstallment;
    @Column(name = "despesa_recorrente")
    private Boolean recurringExpense;
    @Column(name = "dh_criacao")
    private LocalDateTime dhCreate;
    @Column(name = "dh_atualizacao")
    private LocalDateTime dhUpdate;
    @Column(name = "dh_exclusao")
    private LocalDateTime dhExclude;
    @OneToOne
    @JoinColumn(name = "id_categoria")
    private DbCategory dbCategory;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private DbUser dbUser;

    @PreUpdate
    protected void preUpdate() {
        dhUpdate = LocalDateTime.now();
    }
}