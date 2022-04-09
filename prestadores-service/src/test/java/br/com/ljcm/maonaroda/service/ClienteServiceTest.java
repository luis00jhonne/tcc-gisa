package br.com.ljcm.maonaroda.service;

import br.com.ljcm.maonaroda.exceptions.NotFoundException;
import br.com.ljcm.maonaroda.model.Cliente;
import br.com.ljcm.maonaroda.model.Trabalhador;
import br.com.ljcm.maonaroda.repository.ClienteRepository;
import br.com.ljcm.maonaroda.repository.TrabalhadorRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class ClienteServiceTest {
	
	@Autowired
	private ClienteService service;

	@MockBean
	private ClienteRepository repository;

	private static final String CPF = "123.456.789-00";
	
	@Test
	@Order(1)
	public void testSaveVehicle() {
		
		BDDMockito.given(repository.save(Mockito.any(Cliente.class)))
			.willReturn(getTrabalhadorMock());
		Cliente response = service.save(new Cliente());
		
		assertNotNull(response);
		assertEquals("123.456.789-00", response.getCpf());
	}
	
	@Test
	@Order(2)
	public void testFindByCPF() throws NotFoundException {
		
		BDDMockito.given(repository.findOneByCpf(Mockito.anyString()))
			.willReturn(Optional.of(new Cliente()));
		
		Optional<Cliente> response = service.findByCpf(CPF);
		assertTrue(!response.isEmpty());
	}
	
	@Test
	@Order(3)
	public void testFindAllVehicles() {
		
		List<Cliente> transactions = new ArrayList<>();
		transactions.add(getTrabalhadorMock());
		Page<Cliente> page = new PageImpl<>(transactions);
		
		BDDMockito.given(repository.findAll(Mockito.any(Pageable.class))).willReturn(page);
		
		Page<Cliente> response = service.findAll(0, 10, null);
		assertNotNull(response);
	}
	
	private Cliente getTrabalhadorMock() {

		Cliente entity = new Cliente();
		entity.setNome("Cliente");
		entity.setCpf(CPF);
		entity.setEmail("email@email.com");
		entity.setCelular("(98) 98123-9090");
		return entity;
	}
	
	@AfterAll
	private void tearDown() {
		repository.deleteAll();
	}

}