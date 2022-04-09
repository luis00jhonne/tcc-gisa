package br.com.gisa.conveniados;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Log4j2
@SpringBootApplication
public class ConveniadosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConveniadosServiceApplication.class, args);
		log.info("Conveniados Service API started successfully at {}", LocalDateTime.now());
	}

}
