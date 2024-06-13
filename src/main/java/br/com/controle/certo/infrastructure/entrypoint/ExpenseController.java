package br.com.controle.certo.infrastructure.entrypoint;

import br.com.controle.certo.application.usecase.expense.*;
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

    @PostMapping()
    public ResponseEntity<?> postExpense(@Valid @RequestBody RequestExpense body,
                                         @RequestHeader(value = "document") String document) {
        useCase.postExpense(body, document);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<?> getAllExpenseByDocument(@RequestHeader(value = "document") String userDocument) {
        List<ResponseExpensive> response = getExpenseUseCase.getAllExpenseByDocument(userDocument);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/current-month")
    public ResponseEntity<?> getAllExpenseCurrentMonth(@RequestHeader(value = "document") String userDocument) {
        List<ResponseExpensive> response = getExpenseCurrentMonthUseCase.getExpenseCurrentMonth(userDocument);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable(value = "id") Integer idExpense,
                                            @RequestHeader(value = "document") String userDocument) {
        ResponseExpensive response = getExpenseByIdUseCase.getExpenseById(idExpense, userDocument);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteExpenseById(@PathVariable(value = "id") Integer idExpense,
                                               @RequestHeader(value = "document") String userDocument,
                                               @RequestHeader(value = "recursive", defaultValue = "false") Boolean recursive) {
        deleteExpenseUseCase.deleteExpenseById(idExpense, userDocument, recursive);
        return ResponseEntity.noContent().build();
    }

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
