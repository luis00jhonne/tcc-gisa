package br.com.gisa.acessoaolegado.controller;

import br.com.gisa.acessoaolegado.exceptions.CPFExistenteException;
import br.com.gisa.acessoaolegado.exceptions.InvalidUpdateException;
import br.com.gisa.acessoaolegado.exceptions.NotFoundException;
import br.com.gisa.acessoaolegado.model.Associado;
import br.com.gisa.acessoaolegado.enums.SituacaoAssociadoEnum;
import br.com.gisa.acessoaolegado.dto.model.AssociadoDTO;
import br.com.gisa.acessoaolegado.dto.response.Response;
import br.com.gisa.acessoaolegado.service.AssociadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/acesso-legado/v1")
public class AcessoAoLegadoController {

	private AssociadoService conveniadoService;

    @Autowired
    public AcessoAoLegadoController(AssociadoService conveniadoService){
        this.conveniadoService = conveniadoService;
    }

	@ApiOperation(value = "Listar informações cadastrais e histórico de saúde de um associado")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna as informações cadastrais do associado especificado pelo id"),
			@ApiResponse(code = 404, message = "Associado não encontrado."),
			@ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
	@GetMapping(path = "/associado/{id}")
	public ResponseEntity<Response<AssociadoDTO>> listarPorId(@PathVariable Long id) throws NotFoundException {

		Response<AssociadoDTO> response = new Response<>();
		Associado trabalhador = conveniadoService.findById(id);

		AssociadoDTO dto = trabalhador.convertEntityToDTO();

		//buscar historico do associado atraves do sistema de acesso-ao-legado

		createSelfLink(trabalhador, dto);
		response.setData(dto);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

    private void createSelfLink(Associado trabalhador, AssociadoDTO trabalhadorDTO) {
		Link selfLink = WebMvcLinkBuilder.linkTo(AcessoAoLegadoController.class).slash(trabalhador.getId()).withSelfRel();
		trabalhadorDTO.add(selfLink);
	}

	private void createSelfLinkInCollections(final AssociadoDTO trabalhadorDTO)
			throws NotFoundException {
		Link selfLink = linkTo(methodOn(AcessoAoLegadoController.class)
				.listarPorId(trabalhadorDTO.getId()))
				.withSelfRel().expand();
		trabalhadorDTO.add(selfLink);

	}
}
