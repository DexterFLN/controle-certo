package br.com.controle.certo.application.usecase.userauth.impl;

import br.com.controle.certo.application.usecase.userauth.PostUserAuthUseCase;
import br.com.controle.certo.infrastructure.entrypoint.handler.UserException;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUserRegistration;
import br.com.controle.certo.infrastructure.repository.auth.DbUserAuth;
import br.com.controle.certo.infrastructure.repository.auth.DbUserAuthPermission;
import br.com.controle.certo.infrastructure.repository.auth.DbUserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostUserAuthUseCaseImpl implements PostUserAuthUseCase {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DbUserAuthRepository repository;

    @Override
    public void postUserAuth(RequestUserRegistration body) {
        if (repository.existsByUsername(body.getUsername())) {
            throw new UserException("Não foi possível criar o usuário.");
        }

        DbUserAuthPermission permission = DbUserAuthPermission.builder()
                .permissionName("ROLE_USER")
                .build();
        DbUserAuth newUser = DbUserAuth.builder()
                .username(body.getUsername())
                .userPassword(passwordEncoder.encode(body.getPassword()))
                .emailUser(body.getEmail())
                .build();
        permission.setDbUserAuth(newUser);
        newUser.setPermissionList(List.of(permission));

        repository.save(newUser);
    }

    public void changePasswordUserAuth(DbUserAuth userAuth) {
        repository.save(userAuth);
    }
}
