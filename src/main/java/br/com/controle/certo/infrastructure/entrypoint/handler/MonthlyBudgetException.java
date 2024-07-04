package br.com.controle.certo.infrastructure.entrypoint.handler;

public class MonthlyBudgetException extends RuntimeException {
    public MonthlyBudgetException(String message) {
        super(message);
    }
}