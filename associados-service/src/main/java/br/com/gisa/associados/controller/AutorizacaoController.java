package br.com.gisa.associados.controller;

import br.com.gisa.associados.dto.model.AssociadoDTO;
import br.com.gisa.associados.dto.model.AutorizacaoExameConsultaDTO;
import br.com.gisa.associados.dto.response.Response;
import br.com.gisa.associados.exceptions.NotFoundException;
import br.com.gisa.associados.model.Associado;
import br.com.gisa.associados.model.AutorizacaoExameConsulta;
import br.com.gisa.associados.service.AssociadoService;
import br.com.gisa.associados.service.AutorizacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/v1/autorizacao")
public class AutorizacaoController {

	private AssociadoService associadoService;
	private AutorizacaoService autorizacaoService;

    @Autowired
    public AutorizacaoController(AssociadoService associadoService, AutorizacaoService autorizacaoService){
        this.associadoService = associadoService;
		this.autorizacaoService = autorizacaoService;
    }

	@ApiOperation(value = "Autorizar a realização de uma consulta ou exame")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Autorização da consulta/exame realizada com sucesso"),
			@ApiResponse(code = 400, message = "Erro na autorização da consulta/exame."),
			@ApiResponse(code = 204, message = "Exame não autorizado"),
			@ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
	@PostMapping
	public ResponseEntity<Response<AutorizacaoExameConsultaDTO>> autorizarConsultaExame(@Valid @RequestBody AutorizacaoExameConsultaDTO dto, BindingResult result) throws NotFoundException {

		Response<AutorizacaoExameConsultaDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Optional<Associado> associadoEncontrado = associadoService.findByCpf(dto.getAssociado().getCpf());

		if(!associadoEncontrado.isPresent()) {
			throw new NotFoundException("CPF do Associado não encontrado.");
		}

		dto.setAssociado(associadoEncontrado.get().convertEntityToDTO());
		AutorizacaoExameConsulta autorizacao = autorizacaoService.autorizarExameConsulta(dto.convertDTOToEntity());

		AutorizacaoExameConsultaDTO autorizacaoDtoRetorno = autorizacao.convertEntityToDTO();
		response.setData(autorizacaoDtoRetorno);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Consultar informações sobre uma autorização de exame/consulta")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna as informações da autorização especificada"),
			@ApiResponse(code = 404, message = "Código de autorização não encontrado."),
			@ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
	@GetMapping(path = "/{codigoAutorizacao}")
	public ResponseEntity<Response<AutorizacaoExameConsultaDTO>> listarPorId(@PathVariable String codigoAutorizacao) throws NotFoundException {

		Response<AutorizacaoExameConsultaDTO> response = new Response<>();
		AutorizacaoExameConsulta autorizacao = autorizacaoService.consultarExameConsulta(codigoAutorizacao);

		AutorizacaoExameConsultaDTO dto = autorizacao.convertEntityToDTO();

		response.setData(dto);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
    
}
