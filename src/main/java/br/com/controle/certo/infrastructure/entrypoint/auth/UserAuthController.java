package br.com.controle.certo.infrastructure.entrypoint.auth;

import br.com.controle.certo.application.usecase.userauth.PostUserAuthUseCase;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/oauth")
public class UserAuthController {

    @Autowired
    private PostUserAuthUseCase userAuthUseCase;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RequestUserRegistration body) {
        userAuthUseCase.postUserAuth(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}