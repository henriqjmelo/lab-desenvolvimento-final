package com.pucminas.moedaestudantil.config;

import com.pucminas.moedaestudantil.model.*;
import com.pucminas.moedaestudantil.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final ProfessorRepository professorRepository;
    private final EmpresaRepository empresaRepository;
    private final AlunoRepository alunoRepository;
    private final VantagemRepository vantagemRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository,
                            ProfessorRepository professorRepository,
                            EmpresaRepository empresaRepository,
                            AlunoRepository alunoRepository,
                            VantagemRepository vantagemRepository,
                            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.professorRepository = professorRepository;
        this.empresaRepository = empresaRepository;
        this.alunoRepository = alunoRepository;
        this.vantagemRepository = vantagemRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByEmail("joao@puc.br").isEmpty()) {
            Professor professor = new Professor();
            professor.setNome("Professor João");
            professor.setEmail("joao@puc.br");
            professor.setSenha(passwordEncoder.encode("123"));
            professor.setTipo("PROFESSOR");
            professor.setCpf("111.111.111-11");
            professor.setDepartamento("Engenharia");
            professor.setInstituicao("PUC Minas");
            professor.setSaldo(1000);
            professorRepository.save(professor);
        }

        Empresa empresa = (Empresa) usuarioRepository.findByEmail("contato@ru.com").orElse(null);
        if (empresa == null) {
            empresa = new Empresa();
            empresa.setNome("Restaurante Universitário");
            empresa.setEmail("contato@ru.com");
            empresa.setSenha(passwordEncoder.encode("123"));
            empresa.setTipo("EMPRESA");
            empresa.setCnpj("22.222.222/0001-22");
            empresa = empresaRepository.save(empresa);
        }

        if (vantagemRepository.findByEmpresa(empresa).isEmpty()) {
            Vantagem v1 = new Vantagem();
            v1.setDescricao("Almoço Grátis");
            v1.setCusto(50);
            v1.setEmpresa(empresa);
            vantagemRepository.save(v1);

            Vantagem v2 = new Vantagem();
            v2.setDescricao("Desconto 10% Mensalidade");
            v2.setCusto(200);
            v2.setEmpresa(empresa);
            vantagemRepository.save(v2);
        }

        if (usuarioRepository.findByEmail("aluno@puc.br").isEmpty()) {
            Aluno aluno = new Aluno();
            aluno.setNome("Aluno Demo");
            aluno.setEmail("aluno@puc.br");
            aluno.setSenha(passwordEncoder.encode("123"));
            aluno.setTipo("ALUNO");
            aluno.setCpf("222.222.222-22");
            aluno.setRg("MG-22.222.222");
            aluno.setEndereco("Rua Exemplo, 123");
            aluno.setInstituicao("PUC Minas");
            aluno.setCurso("Engenharia de Software");
            aluno.setSaldo(300);
            alunoRepository.save(aluno);
        }
    }
}
