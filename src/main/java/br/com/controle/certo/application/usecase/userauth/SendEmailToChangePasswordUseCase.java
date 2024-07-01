package br.com.controle.certo.application.usecase.userauth;

import javax.mail.MessagingException;

public interface SendEmailToChangePasswordUseCase {
    void sendEmailToUserEmail(String username, String email, String token) throws MessagingException;
}
