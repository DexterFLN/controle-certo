package br.com.controle.certo.infrastructure.entrypoint.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HandlerExceptionApplicationTest {

    private HandlerExceptionApplication handler;

    @BeforeEach
    public void setUp() {
        handler = new HandlerExceptionApplication();
    }

    @Test
    public void testHandleUserException() {
        UserException ex = new UserException("User not found");
        ResponseEntity<?> response = handler.handleUserException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ApiValidationError body = (ApiValidationError) response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.NOT_FOUND.value(), body.getCode());
        assertEquals("User not found", body.getMessage());
    }

    @Test
    public void testHandleUserAuthException() {
        UserAuthException ex = new UserAuthException("Invalid credentials");
        ResponseEntity<?> response = handler.handleUserAuthException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiValidationError body = (ApiValidationError) response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.getCode());
        assertEquals("Invalid credentials", body.getMessage());
    }

    @Test
    public void testHandleCategoryException() {
        CategoryException ex = new CategoryException("Invalid category");
        ResponseEntity<?> response = handler.handleCategoryException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiValidationError body = (ApiValidationError) response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.getCode());
        assertEquals("Invalid category", body.getMessage());
    }

    @Test
    public void testHandleExpenseException() {
        ExpenseException ex = new ExpenseException("Invalid expense");
        ResponseEntity<?> response = handler.handleExpenseException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiValidationError body = (ApiValidationError) response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.getCode());
        assertEquals("Invalid expense", body.getMessage());
    }

    @Test
    public void testHandleMonthlyBudgetException() {
        MonthlyBudgetException ex = new MonthlyBudgetException("Invalid monthly budget");
        ResponseEntity<?> response = handler.handleMonthlyBudgetException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiValidationError body = (ApiValidationError) response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.getCode());
        assertEquals("Invalid monthly budget", body.getMessage());
    }

    @Test
    public void testHandleNotFoundEntity() {
        AccessDeniedException ex = new AccessDeniedException("Access denied");
        ResponseEntity<?> response = handler.handleNotFoundEntity(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        ApiValidationError body = (ApiValidationError) response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.FORBIDDEN.value(), body.getCode());
        assertEquals("Você não possui permissão para executar essa operação.", body.getMessage());
    }
}
