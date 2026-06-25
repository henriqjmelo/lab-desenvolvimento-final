package com.pucminas.moedaestudantil.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.sender.email}")
    private String senderEmail;

    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public void enviarEmail(String destinatario, String assunto, String htmlContent) {
        enviarViaApi(destinatario, assunto, htmlContent);
    }

    @Async
    public void enviarEmailComImagemInline(String destinatario, String assunto, String htmlComCid, String cid, byte[] imagemPng) {
        String nomeArquivo = cid + ".png";
        String htmlComAnexo = htmlComCid.replace("cid:" + cid, "cid:" + nomeArquivo);
        String base64 = Base64.getEncoder().encodeToString(imagemPng);
        enviarViaApi(destinatario, assunto, htmlComAnexo, List.of(Map.of("content", base64, "name", nomeArquivo)));
    }

    private void enviarViaApi(String destinatario, String assunto, String htmlContent) {
        enviarViaApi(destinatario, assunto, htmlContent, null);
    }

    private void enviarViaApi(String destinatario, String assunto, String htmlContent, List<Map<String, String>> anexos) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("api-key", apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> sender = new HashMap<>();
            sender.put("email", senderEmail);
            sender.put("name", "Sistema de Moeda Estudantil");

            Map<String, Object> body = new HashMap<>();
            body.put("sender", sender);
            body.put("to", List.of(Map.of("email", destinatario)));
            body.put("subject", assunto);
            body.put("htmlContent", htmlContent);
            if (anexos != null && !anexos.isEmpty()) {
                body.put("attachment", anexos);
            }

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
            restTemplate.postForEntity("https://api.brevo.com/v3/smtp/email", request, String.class);
        } catch (Exception e) {
            System.out.println("Falha ao enviar email para " + destinatario + ": " + e.getMessage());
        }
    }
}
