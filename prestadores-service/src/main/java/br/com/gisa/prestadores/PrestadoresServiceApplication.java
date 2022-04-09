package br.com.gisa.prestadores;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Log4j2
@SpringBootApplication
public class PrestadoresServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrestadoresServiceApplication.class, args);
		log.info("Prestadores Service API started successfully at {}", LocalDateTime.now());
	}

}
