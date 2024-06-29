package br.com.controle.certo.application.usecase.userauth;


import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestUserRegistrationUpdate;

public interface ChangePasswordUserAuthUseCase {
    void createToken(String username, String email);
    void validateTokenAndResetPassword(String token, String newPassword);
    void changePasswordOnApp(String username, RequestUserRegistrationUpdate body);
}
