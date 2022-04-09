package br.com.gisa.conveniados.service;

import br.com.gisa.conveniados.exceptions.NotFoundException;
import br.com.gisa.conveniados.model.Associado;
import br.com.gisa.conveniados.repository.AssociadoRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class AssociadoServiceTest {
	
	@Autowired
	private AssociadoService service;

	@MockBean
	private AssociadoRepository repository;

	private static final String CPF = "123.456.789-00";
	
	@Test
	@Order(1)
	public void testSaveVehicle() {
		
		BDDMockito.given(repository.save(Mockito.any(Associado.class)))
			.willReturn(getTrabalhadorMock());
		Associado response = service.save(new Associado());
		
		assertNotNull(response);
		assertEquals("123.456.789-00", response.getCpf());
	}
	
	@Test
	@Order(2)
	public void testFindByCPF() throws NotFoundException {
		
		BDDMockito.given(repository.findOneByCpf(Mockito.anyString()))
			.willReturn(Optional.of(new Associado()));
		
		Optional<Associado> response = service.findByCpf(CPF);
		assertTrue(!response.isEmpty());
	}
	
	@Test
	@Order(3)
	public void testFindAllVehicles() {
		
		List<Associado> transactions = new ArrayList<>();
		transactions.add(getTrabalhadorMock());
		Page<Associado> page = new PageImpl<>(transactions);
		
		BDDMockito.given(repository.findAll(Mockito.any(Pageable.class))).willReturn(page);
		
		Page<Associado> response = service.findAll(0, 10, null);
		assertNotNull(response);
	}
	
	private Associado getTrabalhadorMock() {

		Associado entity = new Associado();
		entity.setNome("Trabalhador");
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