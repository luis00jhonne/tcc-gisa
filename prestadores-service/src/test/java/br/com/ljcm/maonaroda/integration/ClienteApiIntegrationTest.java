package br.com.ljcm.maonaroda.integration;

import br.com.ljcm.maonaroda.dto.model.ClienteDTO;
import br.com.ljcm.maonaroda.dto.model.TrabalhadorDTO;
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
public class ClienteApiIntegrationTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	private final String endpoint = "/mao-na-roda/v1/cliente";

	@Test
	@Order(1)
	public void testCreateClienteComId10() {

		ClienteDTO dto = new ClienteDTO();
		dto.setId(10L);
		dto.setNome("Cliente 1");
		dto.setCpf("123.456.789-00");
		dto.setEmail("email@email.com");
		dto.setCelular("(98) 98123-9090");

		// Create a new HttpEntity
		final HttpEntity<ClienteDTO> entity = new HttpEntity<>(dto);

		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + port + endpoint, HttpMethod.POST, entity, String.class);

		assertEquals(201, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(2)
	public void testCreateClienteComId20() {

		ClienteDTO dto = new ClienteDTO();
		dto.setId(20L);
		dto.setNome("Cliente 2");
		dto.setCpf("123.456.789-11");
		dto.setEmail("email@email.com");
		dto.setCelular("(98) 98123-9090");

		// Create a new HttpEntity
		final HttpEntity<ClienteDTO> entity = new HttpEntity<>(dto);

		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + port + endpoint, HttpMethod.POST, entity, String.class);

		assertEquals(201, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(3)
	public void testFindAllClientes() throws ParseException {

		ResponseEntity<String> responseEntity = this.restTemplate.exchange(
				"http://localhost:" + port + endpoint + "?page=0&limit=10", HttpMethod.GET, null, String.class);

		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(5) 
	public void testFindClienteComIdNaoExistente() {
	  
	  ResponseEntity<String> responseEntity = this.restTemplate
	  .exchange("http://localhost:" + port + endpoint + "/37894",
	  HttpMethod.GET, null, String.class);
	  
	  //Transaction not found 
	  assertEquals(404, responseEntity.getStatusCodeValue()); 
	}
	
	@Test
	@Order(7) 
	public void testDeletarClientePorId() {
	  
	  ResponseEntity<String> responseEntity = this.restTemplate.exchange(
			  "http://localhost:" + port + endpoint + "/20",
			  HttpMethod.DELETE, null, String.class);
	  
	  //Transaction not found 
	  assertEquals(404, responseEntity.getStatusCodeValue()); 
	}

}
