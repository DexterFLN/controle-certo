package br.com.controle.certo.infrastructure.repository.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_usuario")
public class DbUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUser;
    @Column(name = "nr_documento")
    private String documentNumber;
    @Column(name = "email_usuario")
    private String emailUser;
    @Column(name = "numero_telefone")
    private String phoneNumber;
    @Column(name = "uf")
    private String uf;
    @Column(name = "municipio")
    private String county;
    @Column(name = "data_aceite_termos")
    private Boolean acceptTerms;
    @Column(name = "lvl_usuario")
    private Integer lvlUser;
    @Column(name = "xp_usuario")
    private Integer xpUser;
    @Column(name = "dh_criacao")
    private LocalDateTime dhCreate;
    @Column(name = "dh_atualizacao")
    private LocalDateTime dhUpdate;
    @OneToMany(mappedBy = "dbUser")
    private List<DbExpense> dbExpenseList;
    @OneToMany
    private List<DbMonthlyBudget> dbMonthlyBudgetList;

    @PrePersist
    protected void prePersist() {
        dhCreate = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        dhUpdate = LocalDateTime.now();
    }
}
