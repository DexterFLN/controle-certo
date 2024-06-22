package br.com.controle.certo.infrastructure.entrypoint;

import br.com.controle.certo.application.usecase.monthly.GetMonthlyBudgetCurrentMonthUseCase;
import br.com.controle.certo.application.usecase.monthly.PostMonthlyBudgetIUseCase;
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
    private GetMonthlyBudgetCurrentMonthUseCase getMonthlyBudgetCurrentMonthUseCase;

    @PostMapping
    public ResponseEntity<?> postMonthlyBudget(@RequestHeader(value = "document") String userDocument,
                                               @RequestBody RequestMonthlyBudget body) {
        monthlyBudgetIUseCase.postMonthlyBudget(userDocument, body);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "/current-month")
    public ResponseEntity<?> getMonthlyBudgetCurrentMonth(@RequestHeader(value = "document") String userDocument) {
        ResponseMonthlyBudget response = getMonthlyBudgetCurrentMonthUseCase.getMonthlyBudgetCurrentMonth(userDocument);
        return ResponseEntity.ok(response);
    }
}