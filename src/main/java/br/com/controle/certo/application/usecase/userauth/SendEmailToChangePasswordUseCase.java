package br.com.controle.certo.application.usecase.userauth;

public interface SendEmailToChangePasswordUseCase {
    void sendEmailToUserEmail(String email, String token);
}
