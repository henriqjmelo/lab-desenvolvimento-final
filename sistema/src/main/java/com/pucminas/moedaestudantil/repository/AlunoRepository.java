package com.pucminas.moedaestudantil.repository;

import com.pucminas.moedaestudantil.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
