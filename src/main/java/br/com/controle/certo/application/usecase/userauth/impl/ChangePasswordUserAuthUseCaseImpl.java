package br.com.controle.certo.application.usecase.userauth.impl;

import br.com.controle.certo.application.usecase.userauth.ChangePasswordUserAuthUseCase;
import br.com.controle.certo.application.usecase.userauth.PostUserAuthUseCase;
import br.com.controle.certo.application.usecase.userauth.SendEmailToChangePasswordUseCase;
import br.com.controle.certo.infrastructure.entrypoint.handler.UserAuthException;
import br.com.controle.certo.infrastructure.entrypoint.handler.UserException;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUserRegistration;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUserRegistrationUpdate;
import br.com.controle.certo.infrastructure.repository.auth.DbPasswordResetToken;
import br.com.controle.certo.infrastructure.repository.auth.DbPasswordResetTokenRepository;
import br.com.controle.certo.infrastructure.repository.auth.DbUserAuth;
import br.com.controle.certo.infrastructure.repository.auth.DbUserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.isNull;

@Component
public class ChangePasswordUserAuthUseCaseImpl implements ChangePasswordUserAuthUseCase {

    private static final int EXPIRATION_HOURS = 1;
    @Autowired
    private DbPasswordResetTokenRepository tokenRepository;
    @Autowired
    private DbUserAuthRepository userAuthRepository;
    @Autowired
    private PostUserAuthUseCase postUserAuthUseCase;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SendEmailToChangePasswordUseCase sendEmailToChangePasswordUseCase;

    @Transactional
    @Override
    public void createToken(String username, String email) throws MessagingException {
        DbUserAuth dbUserAuth = validateUserExists(username, email);

        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(EXPIRATION_HOURS);

        DbPasswordResetToken resetToken = DbPasswordResetToken.builder()
                .token(token)
                .user(dbUserAuth)
                .expiryDate(expiryDate)
                .build();

        resetPassword(dbUserAuth, token, dbUserAuth);
        tokenRepository.save(resetToken);

        sendEmailToChangePasswordUseCase.sendEmailToUserEmail(username, email, token);
    }


    private DbUserAuth validateUserExists(String username, String email) {
        DbUserAuth dbUserAuth = userAuthRepository.findByUsernameAndEmail(username, email);

        if (dbUserAuth == null) {
            throw new UserException("Não foi possivel solicitar troca de senha");
        }
        return dbUserAuth;
    }

    @Transactional
    @Override
    public void validateTokenAndResetPassword(String token, String newPassword) {
        DbPasswordResetToken resetToken = tokenRepository.findByToken(token);

        if (resetToken == null || resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new UserException("Token inválido ou expirado.");
        }

        DbPasswordResetToken tokenUser = tokenRepository.findByToken(token);
        DbUserAuth user = tokenUser.getUser();

        user.setUserPassword(passwordEncoder.encode(newPassword));
        postUserAuthUseCase.changePasswordUserAuth(user);

        tokenRepository.deleteByToken(token);
    }

    @Override
    public void changePasswordOnApp(String username, RequestUserRegistrationUpdate body) {
        DbUserAuth result = userAuthRepository.findByUsernameAndDhExcludeIsNull(username).orElseThrow();

        if (!passwordEncoder.matches(body.getOldPassword(), result.getUserPassword())) {
            throw new UserAuthException("A senha atual não está correta.");
        } else {
            result.setUserPassword(passwordEncoder.encode(body.getNewPassword()));
            userAuthRepository.save(result);
        }
    }

    private void resetPassword(DbUserAuth dbUserAuth, String token, DbUserAuth user) {
        dbUserAuth.setUserPassword(passwordEncoder.encode(token.concat(String.valueOf(token.length() * 2))));
        postUserAuthUseCase.changePasswordUserAuth(user);
    }
}
