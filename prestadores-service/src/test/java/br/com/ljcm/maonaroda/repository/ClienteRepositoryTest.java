package br.com.ljcm.maonaroda.repository;

import br.com.ljcm.maonaroda.model.Cliente;
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
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository repository;

	private static final String CPF = "123.456.789-00";

	@BeforeAll
	private void setUp() {
		Cliente entity = getCliente("123.456.789-00");

		repository.save(entity);
	}

	@Test
	@Order(1)
	public void testSave() {
		Cliente entity = getCliente("123.456.789-11");
		Cliente response = repository.save(entity);
		assertNotNull(response);
	}
	
	@Test
	@Order(2)
	public void testFindByCpf(){
		Optional<Cliente> response = repository.findOneByCpf(CPF);
		assertFalse(response.isEmpty());
	}
	
	@AfterAll
	private void tearDown() {
		repository.deleteAll();
	}

	private Cliente getCliente(String cpf) {
		Cliente entity = new Cliente();
		entity.setNome("Cliente");
		entity.setCpf(cpf);
		entity.setEmail("email@email.com");
		entity.setCelular("(98) 98123-9090");
		return entity;
	}


}