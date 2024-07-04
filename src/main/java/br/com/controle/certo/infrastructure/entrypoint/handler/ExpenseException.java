package br.com.controle.certo.infrastructure.entrypoint.handler;

public class ExpenseException extends RuntimeException {
    public ExpenseException(String message) {
        super(message);
    }
}