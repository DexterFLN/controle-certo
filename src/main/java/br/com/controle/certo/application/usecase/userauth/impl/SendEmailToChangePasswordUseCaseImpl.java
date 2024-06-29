package br.com.controle.certo.application.usecase.userauth.impl;

import br.com.controle.certo.application.usecase.userauth.SendEmailToChangePasswordUseCase;
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

    @Override
    public void sendEmailToUserEmail(String email, String token) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        try {
            String htmlContent = readTemplateEmail("token-email-template.html");

            htmlContent = htmlContent.replace("[LINK_RECUPERACAO]", "http://localhost:5173/login/reset-password?token=" + token);
            htmlContent = htmlContent.replace("[TOKEN]", token);

            helper.setText(htmlContent, true);
            helper.setSubject("Recuperação de senha - Controle Certo");
            helper.setFrom("controlecertotcs@gmail.com");
            helper.setTo(email);

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
