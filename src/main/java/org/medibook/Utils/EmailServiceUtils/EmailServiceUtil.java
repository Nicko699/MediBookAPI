package org.medibook.Utils.EmailServiceUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceUtil {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.frontend.url}")
    private String FRONTEND_URL;

    public EmailServiceUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendPasswordResetEmail(String toEmail, String userName, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Recuperación de contraseña - MediBook");
        message.setText("Hola " + userName + ",\n\n"
                + "Hemos recibido una solicitud para restablecer tu contraseña.\n\n"
                + "Haz clic en el siguiente enlace para continuar (válido por 10 minutos):\n"
                + resetLink + "\n\n"
                + "Si no solicitaste este cambio, ignora este correo.");

        javaMailSender.send(message);
    }

    public String buildResetLink(String resetTokenId, String resetToken) {
        return FRONTEND_URL
                + "?tokenId=" + resetTokenId
                + "&token=" + resetToken;
    }
}
