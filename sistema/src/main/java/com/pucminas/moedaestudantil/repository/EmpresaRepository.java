package com.pucminas.moedaestudantil.repository;

import com.pucminas.moedaestudantil.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
