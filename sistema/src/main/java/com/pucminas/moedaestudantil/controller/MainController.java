package com.pucminas.moedaestudantil.controller;

import com.pucminas.moedaestudantil.model.*;
import com.pucminas.moedaestudantil.repository.*;
import com.pucminas.moedaestudantil.service.MoedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private VantagemRepository vantagemRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private MoedaService moedaService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro/aluno")
    public String formAluno(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "cadastro-aluno";
    }

    @PostMapping("/cadastro/aluno")
    public String salvarAluno(Aluno aluno) {
        aluno.setSenha(passwordEncoder.encode(aluno.getSenha()));
        aluno.setTipo("ALUNO");
        alunoRepository.save(aluno);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(Authentication auth, Model model) {
        Usuario user = usuarioRepository.findByEmail(auth.getName()).orElseThrow();
        model.addAttribute("user", user);
        
        if (user instanceof Aluno) {
            model.addAttribute("vantagens", vantagemRepository.findAll());
            model.addAttribute("transacoes", transacaoRepository.findByRemetenteOrDestinatario(user, user));
            return "home-aluno";
        } else if (user instanceof Professor) {
            model.addAttribute("alunos", alunoRepository.findAll());
            model.addAttribute("transacoes", transacaoRepository.findByRemetenteOrDestinatario(user, user));
            return "home-professor";
        } else {
            return "home-empresa";
        }
    }

    @PostMapping("/enviar-moedas")
    public String enviarMoedas(Authentication auth, Long alunoId, Integer valor, String motivo) {
        Usuario prof = usuarioRepository.findByEmail(auth.getName()).orElseThrow();
        moedaService.enviarMoedas(prof.getId(), alunoId, valor, motivo);
        return "redirect:/home";
    }

    @PostMapping("/trocar-vantagem")
    public String trocarVantagem(Authentication auth, Long vantagemId) {
        Usuario aluno = usuarioRepository.findByEmail(auth.getName()).orElseThrow();
        Vantagem v = vantagemRepository.findById(vantagemId).orElseThrow();
        moedaService.trocarMoedas(aluno.getId(), v);
        return "redirect:/home";
    }
}
