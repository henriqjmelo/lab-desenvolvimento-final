package com.pucminas.moedaestudantil.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmail(String destinatario, String assunto, String texto) {
        try {
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, "UTF-8");
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(texto, true);
            mailSender.send(mensagem);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar email: " + e.getMessage(), e);
        }
    }
}
