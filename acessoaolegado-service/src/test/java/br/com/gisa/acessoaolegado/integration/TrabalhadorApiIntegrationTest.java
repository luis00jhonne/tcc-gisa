package br.com.gisa.acessoaolegado.integration;

import br.com.gisa.acessoaolegado.dto.model.AssociadoDTO;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TrabalhadorApiIntegrationTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	private final String endpoint = "/mao-na-roda/v1/trabalhador";

	@Test
	@Order(1)
	public void testCreateTrabalhadorWithId10() {

		AssociadoDTO dto = new AssociadoDTO();
		dto.setId(10L);
		dto.setNome("Trabalhador");
		dto.setCpf("123.456.789-00");
		dto.setEmail("email@email.com");
		dto.setEspecialidade("Pedreiro");
		dto.setCelular("(98) 98123-9090");

		// Create a new HttpEntity
		final HttpEntity<AssociadoDTO> entity = new HttpEntity<>(dto);

		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + port + endpoint, HttpMethod.POST, entity, String.class);

		assertEquals(201, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(2)
	public void testCreateTrabalhadorWithId20() {

		AssociadoDTO dto = new AssociadoDTO();
		dto.setId(20L);
		dto.setNome("Trabalhador");
		dto.setCpf("123.456.789-11");
		dto.setEmail("email@email.com");
		dto.setEspecialidade("Pedreiro");
		dto.setCelular("(98) 98123-9090");

		// Create a new HttpEntity
		final HttpEntity<AssociadoDTO> entity = new HttpEntity<>(dto);

		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + port + endpoint, HttpMethod.POST, entity, String.class);

		assertEquals(201, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(3)
	public void testFindAllTrabalhador() throws ParseException {

		ResponseEntity<String> responseEntity = this.restTemplate.exchange(
				"http://localhost:" + port + endpoint + "?page=0&limit=10", HttpMethod.GET, null, String.class);

		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(5) 
	public void testFindTrabalhadorComIdNaoExistente() {
	  
	  ResponseEntity<String> responseEntity = this.restTemplate
	  .exchange("http://localhost:" + port + endpoint + "/3",
	  HttpMethod.GET, null, String.class);
	  
	  //Transaction not found 
	  assertEquals(404, responseEntity.getStatusCodeValue()); 
	}
	
	@Test
	@Order(7) 
	public void testDeletarTrabalhadorPorId() {
	  
	  ResponseEntity<String> responseEntity = this.restTemplate.exchange(
			  "http://localhost:" + port + endpoint + "/20",
			  HttpMethod.DELETE, null, String.class);
	  
	  //Transaction not found 
	  assertEquals(404, responseEntity.getStatusCodeValue()); 
	}

}
