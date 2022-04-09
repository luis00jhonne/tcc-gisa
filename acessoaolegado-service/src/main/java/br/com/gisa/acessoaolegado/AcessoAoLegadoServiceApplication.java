package br.com.gisa.acessoaolegado;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Log4j2
@SpringBootApplication
public class AcessoAoLegadoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcessoAoLegadoServiceApplication.class, args);
		log.info("Acesso ao Legado Service API started successfully at {}", LocalDateTime.now());
	}

}
