package br.com.controle.certo.infrastructure.entrypoint;

import br.com.controle.certo.application.usecase.monthly.DeleteMonthlyBudgetUseCase;
import br.com.controle.certo.application.usecase.monthly.GetMonthlyBudgetCurrentMonthUseCase;
import br.com.controle.certo.application.usecase.monthly.GetMonthlyBudgetLastMonthUseCase;
import br.com.controle.certo.application.usecase.monthly.PostMonthlyBudgetIUseCase;
import br.com.controle.certo.infrastructure.config.resourceserver.AuthJwtClaim;
import br.com.controle.certo.infrastructure.config.resourceserver.CheckSecurity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestMonthlyBudget;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseMonthlyBudget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1/monthly")
public class MonthlyBudgetController {

    @Autowired
    private PostMonthlyBudgetIUseCase monthlyBudgetIUseCase;
    @Autowired
    private GetMonthlyBudgetLastMonthUseCase getMonthlyBudgetLastMonthUseCase;
    @Autowired
    private GetMonthlyBudgetCurrentMonthUseCase getMonthlyBudgetCurrentMonthUseCase;
    @Autowired
    private DeleteMonthlyBudgetUseCase deleteMonthlyBudgetUseCase;
    @Autowired
    private AuthJwtClaim authJwtClaim;

    @CheckSecurity.isAuthenticated
    @PostMapping
    public ResponseEntity<?> postMonthlyBudget(@RequestBody RequestMonthlyBudget body) {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        monthlyBudgetIUseCase.postMonthlyBudget(userDocument, body);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.isAuthenticated
    @GetMapping(value = "/current-month")
    public ResponseEntity<?> getMonthlyBudgetCurrentMonth(@RequestHeader(value = "month") int month,
                                                          @RequestHeader(value = "year") int year) {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        ResponseMonthlyBudget response = getMonthlyBudgetCurrentMonthUseCase.getMonthlyBudgetCurrentMonth(userDocument, month, year);
        return ResponseEntity.ok()
                .body(response);
    }

    @CheckSecurity.isAuthenticated
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMonthlyBudget(@PathVariable(value = "id") Integer idMonthlyBudget) {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        deleteMonthlyBudgetUseCase.deleteMonthlyBudget(userDocument, idMonthlyBudget);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.isAuthenticated
    @GetMapping(value = "/last-month")
    public ResponseEntity<?> getMonthlyBudgetLastMonth() {
        String userDocument = authJwtClaim.getDocumentNumberUserFromJwt();
        ResponseMonthlyBudget response = getMonthlyBudgetLastMonthUseCase.getMonthlyBudgetLastMonth(userDocument);
        return ResponseEntity.ok()
                .body(response);
    }
}
