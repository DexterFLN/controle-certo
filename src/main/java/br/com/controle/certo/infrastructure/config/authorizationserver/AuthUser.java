package br.com.controle.certo.infrastructure.config.authorizationserver;

import br.com.controle.certo.infrastructure.repository.auth.DbUserAuth;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthUser extends User {
    private static final Long serialVersionUID = 1L;
    private final String email;

    public AuthUser(DbUserAuth user, Collection<org.springframework.security.core.GrantedAuthority> authorities) {
        super(user.getUsername(), user.getUserPassword(), authorities);
        this.email = user.getEmailUser();
    }
}
