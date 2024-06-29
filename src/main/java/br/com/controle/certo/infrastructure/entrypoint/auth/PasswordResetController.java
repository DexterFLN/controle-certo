package br.com.controle.certo.infrastructure.entrypoint.auth;

import br.com.controle.certo.application.usecase.userauth.ChangePasswordUserAuthUseCase;
import br.com.controle.certo.infrastructure.config.resourceserver.AuthJwtClaim;
import br.com.controle.certo.infrastructure.config.resourceserver.CheckSecurity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUserRegistrationUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/password-reset")
public class PasswordResetController {

    @Autowired
    private ChangePasswordUserAuthUseCase changePasswordUserAuthUseCase;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthJwtClaim authJwtClaim;

    @PostMapping("/request")
    public ResponseEntity<?> requestPasswordReset(@RequestHeader("username") String username,
                                                  @RequestHeader("email") String email) {
        changePasswordUserAuthUseCase.createToken(username, email);
        return ResponseEntity.ok("Token enviado com sucesso para o e-mail registrado.");
    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateTokenAndResetPassword(@RequestHeader("token") String token,
                                                           @RequestHeader("newPassword") String newPassword) {
        changePasswordUserAuthUseCase.validateTokenAndResetPassword(token, newPassword);
        return ResponseEntity.ok("Senha resetada com sucesso.");
    }

    @CheckSecurity.isAuthenticated
    @PostMapping("/change-password")
    public ResponseEntity<?> changePasswordByUserDocument(@RequestBody RequestUserRegistrationUpdate body) {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();

        changePasswordUserAuthUseCase.changePasswordOnApp(userDocument, body);
        return ResponseEntity.ok("Senha alterada com sucesso.");
    }
}
