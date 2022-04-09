package br.com.gisa.associados;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Log4j2
@SpringBootApplication
public class AssociadosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssociadosServiceApplication.class, args);
		log.info("Associados Service API started successfully at {}", LocalDateTime.now());
	}

}
