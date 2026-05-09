package com.pucminas.moedaestudantil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.pucminas.moedaestudantil.model")
public class MoedaEstudantilApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoedaEstudantilApplication.class, args);
	}
}
