package com.pucminas.moedaestudantil.service;

import com.pucminas.moedaestudantil.model.*;
import com.pucminas.moedaestudantil.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MoedaService {

    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private ProfessorRepository professorRepository;
    
    @Autowired
    private TransacaoRepository transacaoRepository;
    
    @Autowired
    private CupomRepository cupomRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void enviarMoedas(Long professorId, Long alunoId, Integer valor, String motivo) {
        Professor prof = professorRepository.findById(professorId).orElseThrow();
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow();

        if (prof.getSaldo() < valor) {
            throw new RuntimeException("Saldo insuficiente");
        }

        prof.setSaldo(prof.getSaldo() - valor);
        aluno.setSaldo(aluno.getSaldo() + valor);

        Transacao t = new Transacao();
        t.setRemetente(prof);
        t.setDestinatario(aluno);
        t.setValor(valor);
        t.setData(LocalDateTime.now());
        t.setMotivo(motivo);

        professorRepository.save(prof);
        alunoRepository.save(aluno);
        transacaoRepository.save(t);
        
        try {
            String corpo =
                    "<div style=\"background:#0f2c5c;padding:40px 20px;font-family:Arial,sans-serif;text-align:center;\">" +
                            "<h1 style=\"color:#ffd700;font-size:48px;margin:0 0 10px;text-shadow:2px 2px 4px rgba(0,0,0,0.4);\">&#127881; VOCÊ GANHOU MOEDAS! &#127881;</h1>" +
                            "<p style=\"color:#ffffff;font-size:20px;margin:0 0 30px;\">Olá, <strong>" + aluno.getNome() + "</strong>!</p>" +
                            "<div style=\"background:#ffffff;border-radius:16px;padding:30px;margin:0 auto;max-width:420px;\">" +
                            "<p style=\"font-size:22px;color:#172b4d;margin:0 0 10px;\">Você recebeu</p>" +
                            "<p style=\"font-size:64px;font-weight:bold;color:#28a745;margin:0;\">+" + valor + "</p>" +
                            "<p style=\"font-size:24px;color:#28a745;margin:0 0 20px;font-weight:bold;\">moedas</p>" +
                            "<p style=\"font-size:16px;color:#5f6d82;margin:0 0 6px;\">Enviado por: <strong>" + prof.getNome() + "</strong></p>" +
                            "<p style=\"font-size:16px;color:#5f6d82;margin:0 0 6px;\">Motivo: <strong>" + motivo + "</strong></p>" +
                            "<hr style=\"border:none;border-top:1px solid #e9eef6;margin:20px 0;\">" +
                            "<p style=\"font-size:18px;color:#172b4d;margin:0;\">Saldo atual</p>" +
                            "<p style=\"font-size:36px;font-weight:bold;color:#0d6efd;margin:0;\">" + aluno.getSaldo() + " moedas</p>" +
                            "</div>" +
                            "<p style=\"color:#9fb3d1;font-size:13px;margin-top:30px;\">Sistema de Moeda Estudantil &mdash; RU Restaurante Universitário</p>" +
                            "</div>";

            emailService.enviarEmail(
                    aluno.getEmail(),
                    "🎉 Você recebeu " + valor + " moedas!",
                    corpo
            );
        } catch (Exception e) {
            System.out.println("Falha ao enviar email para " + aluno.getEmail() + ": " + e.getMessage());
        }
    }

    @Transactional
    public Cupom trocarMoedas(Long alunoId, Vantagem vantagem) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow();

        if (aluno.getSaldo() < vantagem.getCusto()) {
            throw new RuntimeException("Saldo insuficiente");
        }

        aluno.setSaldo(aluno.getSaldo() - vantagem.getCusto());

        Cupom cupom = new Cupom();
        cupom.setCodigo(UUID.randomUUID().toString());
        cupom.setAluno(aluno);
        cupom.setVantagem(vantagem);

        alunoRepository.save(aluno);
        cupomRepository.save(cupom);

        System.out.println("Email enviado para " + aluno.getEmail() + ": Seu cupom é " + cupom.getCodigo());
        System.out.println("Email enviado para " + vantagem.getEmpresa().getEmail() + ": Cupom gerado para " + aluno.getNome());

        return cupom;
    }
}
