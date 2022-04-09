package br.com.ljcm.maonaroda;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Log4j2
@SpringBootApplication
public class MaoNaRodaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaoNaRodaApplication.class, args);
		log.info("Mao na Roda API started successfully at {}", LocalDateTime.now());
	}

}
