package br.com.controle.certo.infrastructure.entrypoint.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class HandlerExceptionApplication {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String, Object>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, Object>> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("code", fieldName);
            errorMap.put("message", errorMessage);
            errors.add(errorMap);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUserException(UserException ex) {
        ApiValidationError result = ApiValidationError.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<?> handleCategoryException(CategoryException ex) {
        ApiValidationError result = ApiValidationError.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpenseException.class)
    public ResponseEntity<?> handleExpenseException(ExpenseException ex) {
        ApiValidationError result = ApiValidationError.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MonthlyBudgetException.class)
    public ResponseEntity<?> handleMonthlyBudgetException(MonthlyBudgetException ex) {
        ApiValidationError result = ApiValidationError.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleNotFoundEntity(AccessDeniedException ex) {
        ApiValidationError result = ApiValidationError.builder()
                .message("Você não possui permissão para executar essa operação.")
                .code(HttpStatus.FORBIDDEN.value())
                .build();
        return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
    }
}
