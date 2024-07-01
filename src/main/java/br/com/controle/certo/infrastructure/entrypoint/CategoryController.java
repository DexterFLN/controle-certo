package br.com.controle.certo.infrastructure.entrypoint;

import br.com.controle.certo.application.usecase.category.*;
import br.com.controle.certo.infrastructure.config.resourceserver.AuthJwtClaim;
import br.com.controle.certo.infrastructure.config.resourceserver.CheckSecurity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/v1/category")
public class CategoryController {

    @Autowired
    private PostCategoryUseCase postCategoryUseCase;
    @Autowired
    private GetCategoryAllUseCase getCategoryAllUseCase;
    @Autowired
    private GetCategoryUseCase getCategoryUseCase;
    @Autowired
    private DeleteCategoryUseCase deleteCategoryUseCase;
    @Autowired
    private PutCategoryUseCase putCategoryUseCase;
    @Autowired
    private AuthJwtClaim authJwtClaim;

    @CheckSecurity.isAuthenticated
    @PostMapping()
    public ResponseEntity<?> postCategory(@Valid @RequestBody RequestCategory body) {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        ResponseCategory response = postCategoryUseCase.postCategory(body, userDocument);
        return ResponseEntity.ok(response);
    }
    @CheckSecurity.isAuthenticated
    @GetMapping()
    public ResponseEntity<?> getAllCategory() {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        return ResponseEntity.ok(getCategoryAllUseCase.getAllCategory(userDocument));
    }
    @CheckSecurity.isAuthenticated
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable(value = "id") Integer idCategory, @RequestHeader(value = "document") String document) {
        return ResponseEntity.ok(getCategoryUseCase.getCategoryById(document, idCategory));
    }
    @CheckSecurity.isAuthenticated
    @PutMapping(value = "/{id}/update")
    public ResponseEntity<?> updateCategoryById(@PathVariable(value = "id") Integer idCategory,
                                                @RequestBody RequestCategory body) {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        putCategoryUseCase.updateCategoryById(userDocument, idCategory, body);
        return ResponseEntity.ok().build();
    }
    @CheckSecurity.isAuthenticated
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable(value = "id") Integer idCategory) {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        deleteCategoryUseCase.deleteCategoryById(userDocument, idCategory);
        return ResponseEntity.noContent().build();
    }

}