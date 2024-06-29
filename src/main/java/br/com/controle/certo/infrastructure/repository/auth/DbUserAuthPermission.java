package br.com.controle.certo.infrastructure.repository.auth;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_usuario_permission_auth")
public class DbUserAuthPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permissao")
    private Integer idPermission;

    @Column(name = "nome_permissao")
    private String permissionName;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private DbUserAuth dbUserAuth;
}