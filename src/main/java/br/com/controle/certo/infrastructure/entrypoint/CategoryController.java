package br.com.controle.certo.infrastructure.entrypoint;

import br.com.controle.certo.application.usecase.category.*;
import br.com.controle.certo.domain.entities.CategoryEntity;
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

    @PostMapping()
    public ResponseEntity<?> postCategory(@Valid @RequestBody RequestCategory body, @RequestHeader(value = "document") String userDocument) {
        ResponseCategory response = postCategoryUseCase.postCategory(body, userDocument);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<?> getAllCategory(@RequestHeader(value = "document") String document) {
        return ResponseEntity.ok(getCategoryAllUseCase.getAllCategory(document));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable(value = "id") Integer idCategory, @RequestHeader(value = "document") String document) {
        return ResponseEntity.ok(getCategoryUseCase.getCategoryById(document, idCategory));
    }

    @PutMapping(value = "/{id}/update")
    public ResponseEntity<?> updateCategoryById(@PathVariable(value = "id") Integer idCategory,
                                                @RequestBody RequestCategory body,
                                                @RequestHeader(value = "document") String document) {
        putCategoryUseCase.updateCategoryById(document, idCategory, body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable(value = "id") Integer idCategory, @RequestHeader(value = "document") String document) {
        deleteCategoryUseCase.deleteCategoryById(document, idCategory);
        return ResponseEntity.noContent().build();
    }

}