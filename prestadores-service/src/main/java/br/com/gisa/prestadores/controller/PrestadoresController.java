package br.com.gisa.prestadores.controller;

import br.com.gisa.prestadores.exceptions.CPFExistenteException;
import br.com.gisa.prestadores.exceptions.InvalidUpdateException;
import br.com.gisa.prestadores.exceptions.NotFoundException;
import br.com.gisa.prestadores.model.Associado;
import br.com.gisa.prestadores.enums.SituacaoAssociadoEnum;
import br.com.gisa.prestadores.dto.model.AssociadoDTO;
import br.com.gisa.prestadores.dto.response.Response;
import br.com.gisa.prestadores.service.AssociadoService;
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
@RequestMapping("/prestadores/v1")
public class PrestadoresController {

	private AssociadoService prestadoreService;

    @Autowired
    public PrestadoresController(AssociadoService prestadoreService){
        this.prestadoreService = prestadoreService;
    }

    @ApiOperation(value = "Listar todos os prestadores")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma lista com prestadores, considerando os filtros"),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping
    public ResponseEntity<Response<List<AssociadoDTO>>> listarTodos(
            @RequestParam(required = false) String filterName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit){
    	
    	Response<List<AssociadoDTO>> response = new Response<>();
    	
    	Page<Associado> list = prestadoreService.findAll(page, limit, filterName);
		
		List<AssociadoDTO> itemsDTO = new ArrayList<>();
		if (list.hasContent()) {
			list.stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
			itemsDTO.stream().forEach(dto -> {
				try {
					createSelfLinkInCollections(dto);
				} catch (NotFoundException e) {
					log.error("Nenhum prestador cadastrado.");
				}
			});
		}
		
		response.setData(itemsDTO);
		response.setPage(list.getPageable());
		response.setTotalPages(list.getTotalPages());
		response.setTotalElements(list.getTotalElements());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Listar informações cadastrais de um prestador")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna as informações cadastrais do prestador especificado pelo id"),
            @ApiResponse(code = 404, message = "Associado não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<AssociadoDTO>> listarPorId(@PathVariable Long id) throws NotFoundException {
    	
    	Response<AssociadoDTO> response = new Response<>();
    	Associado trabalhador = prestadoreService.findById(id);

		AssociadoDTO dto = trabalhador.convertEntityToDTO();
    	
    	createSelfLink(trabalhador, dto);
    	response.setData(dto);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @ApiOperation(value = "Cadastrar um novo prestador")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Prestador cadastrado com sucesso"),
    		 @ApiResponse(code = 400, message = "Os dados da requisição estão incompletos ou incorretos."),
    		@ApiResponse(code = 204, message = "Erro ao cadastrar o prestador."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PostMapping
    public ResponseEntity<Response<AssociadoDTO>> salvarAssociado(@Valid @RequestBody AssociadoDTO dto,  BindingResult result) throws NotFoundException, CPFExistenteException {

    	Response<AssociadoDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Optional<Associado> vehicleToFind = prestadoreService.findByCpf(dto.getCpf());
		
		if(vehicleToFind.isPresent()) {
			throw new CPFExistenteException("CPF já cadastrado.");
		}

		dto.setSituacao(SituacaoAssociadoEnum.SUSPENSO);
		dto.setDataCadastro(new Date());
		Associado trabalhadorToCreate = prestadoreService.save(dto.convertDTOToEntity());

		AssociadoDTO dtoSaved = trabalhadorToCreate.convertEntityToDTO();
		createSelfLink(trabalhadorToCreate, dtoSaved);

		response.setData(dtoSaved);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

	@ApiOperation(value = "Atualizar o cadastro do prestador")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastro do prestador atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Os dados da requisição estão incompletos ou incorretos."),
            @ApiResponse(code = 404, message = "Prestador não encontrado."),
            @ApiResponse(code = 409, message = "Tentativa de atualização inválida."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PutMapping(path="/{id}")
    public ResponseEntity<Response<AssociadoDTO>> atualizarAssociado(@PathVariable("id") Long id, @Valid @RequestBody AssociadoDTO dto, BindingResult result) throws NotFoundException, InvalidUpdateException {

    	Response<AssociadoDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Associado trabalhadorFind = prestadoreService.findById(id);
		if (!trabalhadorFind.getId().equals(dto.getId()) ) {
			throw new InvalidUpdateException("Não foi possível alterar o id do cadastro = " + dto.getId());
		}
		
		if (!trabalhadorFind.getCpf().equals(dto.getCpf()) ) {
			throw new InvalidUpdateException("Não foi possível alterar o CPF do cadastro =" + dto.getCpf());
		}

		dto.setDataAtualizacao(new Date());
		Associado trabalhadorUpdate = prestadoreService.save(dto.convertDTOToEntity());
		
		AssociadoDTO itemDTO = trabalhadorUpdate.convertEntityToDTO();
		createSelfLink(trabalhadorUpdate, itemDTO);
		response.setData(itemDTO);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Inativar o cadastro de um prestador")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Prestador inativado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição incorreta."),
            @ApiResponse(code = 404, message = "Associado não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Response<String>> inativarAssociado(@PathVariable("id") Long id) throws NotFoundException{

    	Response<String> response = new Response<>();
		Associado trabalhador = prestadoreService.findById(id);
		
		prestadoreService.deleteById(trabalhador.getId());
		response.setData("Prestador de id = " + trabalhador.getId() + " deletado com sucesso.");
		
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
    
    private void createSelfLink(Associado trabalhador, AssociadoDTO trabalhadorDTO) {
		Link selfLink = WebMvcLinkBuilder.linkTo(PrestadoresController.class).slash(trabalhador.getId()).withSelfRel();
		trabalhadorDTO.add(selfLink);
	}
	
	private void createSelfLinkInCollections(final AssociadoDTO trabalhadorDTO)
			throws NotFoundException {
		Link selfLink = linkTo(methodOn(PrestadoresController.class)
				.listarPorId(trabalhadorDTO.getId()))
				.withSelfRel().expand();
		trabalhadorDTO.add(selfLink);
	
	}
}
