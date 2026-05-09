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
        
        System.out.println("Email enviado para " + aluno.getEmail() + ": Você recebeu " + valor + " moedas!");
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
