package br.com.controle.certo.infrastructure.repository.auth;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_usuario_auth")
public class DbUserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUser;

    @Column(name = "login_usuario")
    private String username;

    @Column(name = "senha_usuario")
    private String userPassword;

    @Column(name = "email_usuario")
    private String emailUser;

    @OneToMany(mappedBy = "dbUserAuth", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DbUserAuthPermission> permissionList = new ArrayList<>();

    @Column(name = "dh_criacao")
    private LocalDateTime dhCreate;

    @Column(name = "dh_atualizacao")
    private LocalDateTime dhUpdate;

    @Column(name = "dh_exclusao")
    private LocalDateTime dhExclude;

    @PrePersist
    protected void prePersist() {
        dhCreate = LocalDateTime.now();
    }

    public static DbUserAuth createUserWithPermission(String username, String userPassword, String emailUser, String permissionName) {
        DbUserAuthPermission permission = DbUserAuthPermission.builder()
                .permissionName(permissionName)
                .build();
        DbUserAuth user = DbUserAuth.builder()
                .username(username)
                .userPassword(userPassword)
                .emailUser(emailUser)
                .build();
        permission.setDbUserAuth(user);
        user.getPermissionList().add(permission);
        return user;
    }
}