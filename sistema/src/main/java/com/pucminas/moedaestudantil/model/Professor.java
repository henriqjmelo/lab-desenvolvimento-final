package com.pucminas.moedaestudantil.model;

import jakarta.persistence.Entity;

@Entity
public class Professor extends Usuario {
    private String cpf;
    private String departamento;
    private String instituicao;
    private Integer saldo = 1000;

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }
    public Integer getSaldo() { return saldo; }
    public void setSaldo(Integer saldo) { this.saldo = saldo; }
}
