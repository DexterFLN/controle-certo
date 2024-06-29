package br.com.controle.certo.infrastructure.config.authorizationserver;

import br.com.controle.certo.infrastructure.repository.auth.DbUserAuth;
import br.com.controle.certo.infrastructure.repository.auth.DbUserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private DbUserAuthRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DbUserAuth user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuáio não encontrado."));
        return new AuthUser(user, getAuthorities(user));
    }

    private Collection<GrantedAuthority> getAuthorities(DbUserAuth user) {
        return user.getPermissionList().stream()
                .map(m -> new SimpleGrantedAuthority(m.getPermissionName().toUpperCase())).collect(Collectors.toList());
    }
}
