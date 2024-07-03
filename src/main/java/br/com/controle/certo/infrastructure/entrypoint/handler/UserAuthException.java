package br.com.controle.certo.infrastructure.entrypoint.handler;

public class UserAuthException extends RuntimeException {
    public UserAuthException(String message) {
        super(message);
    }
}
