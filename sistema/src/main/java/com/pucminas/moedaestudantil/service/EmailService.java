package com.pucminas.moedaestudantil.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void enviarEmail(String destinatario, String assunto, String texto) {
        try {
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, "UTF-8");
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(texto, true);
            mailSender.send(mensagem);
        } catch (Exception e) {
            System.out.println("Falha ao enviar email para " + destinatario + ": " + e.getMessage());
        }
    }

    @Async
    public void enviarEmailComImagemInline(String destinatario, String assunto, String htmlComCid, String cid, byte[] imagemPng) {
        try {
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(htmlComCid, true);
            helper.addInline(cid, new org.springframework.core.io.ByteArrayResource(imagemPng), "image/png");
            mailSender.send(mensagem);
        } catch (Exception e) {
            System.out.println("Falha ao enviar email para " + destinatario + ": " + e.getMessage());
        }
    }
}
