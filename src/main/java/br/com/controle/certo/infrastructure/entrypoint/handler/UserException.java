package br.com.controle.certo.infrastructure.entrypoint.handler;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
