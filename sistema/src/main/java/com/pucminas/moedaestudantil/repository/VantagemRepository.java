package com.pucminas.moedaestudantil.repository;

import com.pucminas.moedaestudantil.model.Empresa;
import com.pucminas.moedaestudantil.model.Vantagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VantagemRepository extends JpaRepository<Vantagem, Long> {
    List<Vantagem> findByEmpresa(Empresa empresa);
}
