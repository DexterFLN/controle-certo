package br.com.controle.certo.application.usecase.userauth.impl;

import br.com.controle.certo.application.usecase.userauth.SendEmailToChangePasswordUseCase;
import br.com.controle.certo.infrastructure.repository.auth.DbUserAuthRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class SendEmailToChangePasswordUseCaseImpl implements SendEmailToChangePasswordUseCase {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private DbUserRepository userRepository;

    @Override
    public void sendEmailToUserEmail(String username, String email, String token) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8"); // Alteração aqui para adicionar true e o charset

            DbUser user = userRepository.getUserByDocument(username);

            String htmlContent = readTemplateEmail("token-email-template.html");

            htmlContent = htmlContent.replace("[LINK_RECUPERACAO]", "http://localhost:5173/login/reset-password?token=" + token);
            htmlContent = htmlContent.replace("[TOKEN]", token);
            htmlContent = htmlContent.replace("[NOME_CLIENTE]", user.getUserName());
            htmlContent = htmlContent.replace("src=\"logo_controle_certo.webp\"", "cid:logo-controle-certo");

            helper.setText(htmlContent, true);
            helper.setSubject("Recuperação de senha - Controle Certo");
            helper.setFrom("controlecertotcs@gmail.com");
            helper.setTo(email);

            ClassPathResource logoImage = new ClassPathResource("templates/logo_controle_certo.webp");
            helper.addInline("logo-controle-certo", logoImage);

            javaMailSender.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    private String readTemplateEmail(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/" + fileName);
        byte[] contentBytes = new byte[(int) resource.contentLength()];
        resource.getInputStream().read(contentBytes);
        return new String(contentBytes, StandardCharsets.UTF_8);
    }
}
