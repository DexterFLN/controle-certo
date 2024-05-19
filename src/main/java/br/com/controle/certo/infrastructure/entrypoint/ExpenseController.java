//package br.com.controle.certo.infrastructure.entrypoint;
//
//import br.com.controle.certo.domain.service.CategoryService.DeleteCategoryService;
//import br.com.controle.certo.domain.service.CategoryService.GetCategoryService;
//import br.com.controle.certo.domain.service.CategoryService.PostCategoryService;
//import br.com.controle.certo.domain.service.CategoryService.PutCategoryService;
//import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
//import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController(value = "/v1/category")
//@Controller
//public class ExpenseController {
//
//    @Autowired
//    private PostCategoryService saveCategoryService;
//    @Autowired
//    private DeleteCategoryService deleteCategoryService;
//    @Autowired
//    private PutCategoryService putCategoryService;
//    @Autowired
//    private GetCategoryService getCategoryService;
//
//    @PostMapping()
//    public ResponseEntity<?> saveCategory(@RequestBody RequestCategory body,
//                                          @RequestHeader(value = "document") String userDocument) {
//        saveCategoryService.saveCategory(body);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping()
//    public ResponseEntity<?> getAllCategory(@RequestHeader(value = "document") String userDocument) {
//        List<ResponseCategory> response = getCategoryService.getAllCategory(userDocument);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<?> getCategoryById(@RequestHeader(value = "document") String userDocument,
//                                             @PathVariable(value = "id") Integer idCategory) {
//        deleteCategoryService.deleteCategory(userDocument, idCategory);
//        return ResponseEntity.ok(List.of(""));
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<?> updateCategory(@RequestHeader(value = "document") String userDocument,
//                                            @PathVariable(value = "id") Integer idCategory,
//                                            @RequestBody RequestCategory body) {
//
//        return ResponseEntity.ok(List.of(""));
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<?> deleteCategory(@RequestHeader(value = "document") String userDocument,
//                                            @PathVariable(value = "id") Integer idCategory) {
//        deleteCategoryService.deleteCategory(userDocument, idCategory);
//        return ResponseEntity.noContent().build();
//    }
//}
