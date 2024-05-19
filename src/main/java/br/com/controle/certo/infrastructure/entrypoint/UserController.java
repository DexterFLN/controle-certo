package br.com.controle.certo.infrastructure.entrypoint;

import br.com.controle.certo.application.usecase.category.GetCategoryAllUseCase;
import br.com.controle.certo.application.usecase.category.GetCategoryUseCase;
import br.com.controle.certo.application.usecase.category.PostCategoryUseCase;
import br.com.controle.certo.application.usecase.user.PostUserUseCase;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/v1/user")
public class UserController {

    @Autowired
    private PostUserUseCase postUserUseCase;
    @Autowired
    private GetCategoryAllUseCase getCategoryAllUseCase;
    @Autowired
    private GetCategoryUseCase getCategoryUseCase;

    @PostMapping()
    public ResponseEntity<?> saveCategory(@RequestBody RequestUser body, @RequestHeader(value = "document") String document) {
        postUserUseCase.postUser(body, document);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<?> getAllCategory(@RequestHeader(value = "document") String document) {
        return ResponseEntity.ok(getCategoryAllUseCase.getAllCategory(document));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable(value = "id") Integer idCategory, @RequestHeader(value = "document") String document) {
        return ResponseEntity.ok(getCategoryUseCase.getCategoryById(document, idCategory));
    }

//    @PutMapping()
//    public ResponseEntity<?> updateCategoryById(@RequestBody RequestCategory body, @RequestHeader(value = "document") String document) {
//        postCategoryUseCase.saveCategory(body, document);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping()
//    public ResponseEntity<?> deleteCategoryById(@RequestBody RequestCategory body, @RequestHeader(value = "document") String document) {
//        postCategoryUseCase.saveCategory(body, document);
//        return ResponseEntity.ok().build();
//    }

}
