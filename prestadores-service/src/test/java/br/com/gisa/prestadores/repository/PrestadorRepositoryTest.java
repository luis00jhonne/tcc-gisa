package br.com.gisa.prestadores.repository;

import br.com.gisa.prestadores.model.Prestador;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public class PrestadorRepositoryTest {
	
	@Autowired
	private PrestadorRepository repository;
	
	private static final String CPF = "123.456.789-00";
	
	@BeforeAll
	private void setUp() {

		Prestador entity = getTrabalhador("123.456.789-00");

		repository.save(entity);
	}

	@Test
	@Order(1)
	public void testSave() {
		
		Prestador entity = getTrabalhador("123.456.789-11");
		Prestador response = repository.save(entity);
		assertNotNull(response);
	}
	
	@Test
	@Order(2)
	public void testFindByCpf(){
		
		Optional<Prestador> response = repository.findOneByCpf(CPF);
		assertFalse(response.isEmpty());
	}
	
	@AfterAll
	private void tearDown() {
		repository.deleteAll();
	}

	private Prestador getTrabalhador(String cpf) {
		Prestador entity = new Prestador();
		entity.setNome("Trabalhador");
		entity.setCpf(cpf);
		entity.setEmail("email@email.com");
		entity.setCelular("(98) 98123-9090");
		return entity;
	}


}