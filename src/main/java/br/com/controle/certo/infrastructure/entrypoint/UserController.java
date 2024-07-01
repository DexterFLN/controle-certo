package br.com.controle.certo.infrastructure.entrypoint;

import br.com.controle.certo.application.usecase.user.*;
import br.com.controle.certo.infrastructure.config.resourceserver.AuthJwtClaim;
import br.com.controle.certo.infrastructure.config.resourceserver.CheckSecurity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestPutUser;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUser;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/v1/user")
public class UserController {

    @Autowired
    private PostUserUseCase postUserUseCase;
    @Autowired
    private PutUserUseCase putUserUseCase;
    @Autowired
    private GetUserAllUseCase getUserAllUseCase;
    @Autowired
    private GetUserByUserDocumentUseCase getUserByUserDocumentUseCase;
    @Autowired
    private DeleteUserUseCase deleteUserUseCase;
    @Autowired
    private AuthJwtClaim authJwtClaim;

    @CheckSecurity.isAuthenticated
    @PostMapping()
    public ResponseEntity<?> postUser(@Valid @RequestBody RequestUser body) {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        ResponseUser result = postUserUseCase.postUser(body, userDocument);
        return ResponseEntity.ok(result);
    }

    @CheckSecurity.hasAuthorityAdmin
    @GetMapping("/all")
    public ResponseEntity<?> getUserAll() {
        return ResponseEntity.ok(getUserAllUseCase.getUserAll());
    }

    @CheckSecurity.isAuthenticated
    @GetMapping(value = "/document")
    public ResponseEntity<?> getUserByUserDocument() {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        return ResponseEntity.ok(getUserByUserDocumentUseCase.getUserByUserDocument(userDocument));
    }

    @CheckSecurity.isAuthenticated
    @PutMapping()
    public ResponseEntity<?> updateUserByDocument(@RequestBody RequestPutUser body) {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        putUserUseCase.putUserByUserDocument(body, userDocument);
        return ResponseEntity.ok().build();
    }

    @CheckSecurity.hasAuthorityAdmin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserByUserDocument(@PathVariable(value = "id") Integer id) {
        deleteUserUseCase.deleteUserByUserDocument(id);
        return ResponseEntity.noContent().build();
    }
}