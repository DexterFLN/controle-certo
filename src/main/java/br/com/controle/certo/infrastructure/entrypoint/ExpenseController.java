package br.com.controle.certo.infrastructure.entrypoint;

import br.com.controle.certo.application.usecase.expense.*;
import br.com.controle.certo.infrastructure.config.resourceserver.AuthJwtClaim;
import br.com.controle.certo.infrastructure.config.resourceserver.CheckSecurity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestExpense;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestPutExpense;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseExpensive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/v1/expense")
public class ExpenseController {

    @Autowired
    private PostExpenseUseCase useCase;
    @Autowired
    private GetExpenseUseCase getExpenseUseCase;
    @Autowired
    private GetExpenseByIdUseCase getExpenseByIdUseCase;
    @Autowired
    private GetExpenseCurrentMonthUseCase getExpenseCurrentMonthUseCase;
    @Autowired
    private DeleteExpenseUseCase deleteExpenseUseCase;
    @Autowired
    private PutExpenseUseCase putExpenseUseCase;
    @Autowired
    private AuthJwtClaim authJwtClaim;

    @CheckSecurity.isAuthenticated
    @PostMapping()
    public ResponseEntity<?> postExpense(@Valid @RequestBody RequestExpense body) {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        useCase.postExpense(body, userDocument);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.isAuthenticated
    @GetMapping()
    public ResponseEntity<?> getAllExpenseByDocument(@RequestHeader(value = "document") String userDocument) {
        List<ResponseExpensive> response = getExpenseUseCase.getAllExpenseByDocument(userDocument);
        return ResponseEntity.ok(response);
    }

    @CheckSecurity.isAuthenticated
    @GetMapping("/current-month")
    public ResponseEntity<?> getAllExpenseCurrentMonth(@RequestHeader(value = "month") int month,
                                                       @RequestHeader(value = "year") int year) {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        List<ResponseExpensive> response = getExpenseCurrentMonthUseCase.getExpenseCurrentMonth(userDocument, month, year);
        return ResponseEntity.ok(response);
    }

    @CheckSecurity.isAuthenticated
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable(value = "id") Integer idExpense,
                                            @RequestHeader(value = "document") String userDocument) {
        ResponseExpensive response = getExpenseByIdUseCase.getExpenseById(idExpense, userDocument);
        return ResponseEntity.ok(response);
    }

    @CheckSecurity.isAuthenticated
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteExpenseById(@PathVariable(value = "id") Integer idExpense,
                                               @RequestHeader(value = "recursive", defaultValue = "false") Boolean recursive,
                                               @RequestHeader(value = "type") String expenseType,
                                               @RequestHeader(value = "month") int month,
                                               @RequestHeader(value = "year") int year
    ) {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        deleteExpenseUseCase.deleteExpenseById(idExpense, userDocument, recursive,expenseType, month, year);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.isAuthenticated
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateExpenseById(@PathVariable(value = "id") Integer idExpense,
                                               @RequestHeader(value = "document") String userDocument,
                                               @RequestHeader(value = "recursive", defaultValue = "false") Boolean recursive,
                                               @Valid @RequestBody RequestPutExpense body
    ) {
        putExpenseUseCase.updateExpenseById(idExpense, userDocument, recursive, body);
        return ResponseEntity.noContent().build();
    }
}
