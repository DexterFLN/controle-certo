package br.com.controle.certo.infrastructure.entrypoint;

import br.com.controle.certo.application.usecase.category.*;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> saveCategory(@RequestBody RequestCategory body, @RequestHeader(value = "document") String document) {
        postCategoryUseCase.saveCategory(body, document);
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
        return ResponseEntity.ok().build();
    }

}
