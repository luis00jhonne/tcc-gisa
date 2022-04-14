package br.com.gisa.prestadores.controller;

import br.com.gisa.prestadores.exceptions.CPFExistenteException;
import br.com.gisa.prestadores.exceptions.InvalidUpdateException;
import br.com.gisa.prestadores.exceptions.NotFoundException;
import br.com.gisa.prestadores.model.Prestador;
import br.com.gisa.prestadores.enums.SituacaoPrestadorEnum;
import br.com.gisa.prestadores.dto.model.PrestadorDTO;
import br.com.gisa.prestadores.dto.response.Response;
import br.com.gisa.prestadores.service.PrestadorService;
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
@RequestMapping("/v1/prestadores")
public class PrestadoresController {

	private PrestadorService prestadorService;

    @Autowired
    public PrestadoresController(PrestadorService prestadoreService){
        this.prestadorService = prestadoreService;
    }

    @ApiOperation(value = "Listar todos os prestadores")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma lista com prestadores, considerando os filtros"),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping
    public ResponseEntity<Response<List<PrestadorDTO>>> listarTodos(
            @RequestParam(required = false) String filterName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit){
    	
    	Response<List<PrestadorDTO>> response = new Response<>();
    	
    	Page<Prestador> list = prestadorService.findAll(page, limit, filterName);
		
		List<PrestadorDTO> itemsDTO = new ArrayList<>();
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
            @ApiResponse(code = 404, message = "Prestador não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<PrestadorDTO>> listarPorId(@PathVariable Long id) throws NotFoundException {
    	
    	Response<PrestadorDTO> response = new Response<>();
    	Prestador prestador = prestadorService.findById(id);

		PrestadorDTO dto = prestador.convertEntityToDTO();
    	
    	createSelfLink(prestador, dto);
    	response.setData(dto);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @ApiOperation(value = "Cadastrar um novo prestador")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Prestador cadastrado com sucesso"),
    		 @ApiResponse(code = 400, message = "Os dados da requisição estão incompletos ou incorretos."),
    		@ApiResponse(code = 204, message = "Erro ao cadastrar o prestador."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PostMapping
    public ResponseEntity<Response<PrestadorDTO>> salvarPrestador(@Valid @RequestBody PrestadorDTO dto, BindingResult result) throws NotFoundException, CPFExistenteException {

    	Response<PrestadorDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		if(prestadorService.findByCpf(dto.getCpf()).isPresent()) {
			throw new CPFExistenteException("CPF já cadastrado.");
		}

		dto.setSituacao(SituacaoPrestadorEnum.ATIVO);
		dto.setDataCadastro(new Date());
		Prestador prestador = prestadorService.save(dto.convertDTOToEntity());

		PrestadorDTO dtoSaved = prestador.convertEntityToDTO();
		createSelfLink(prestador, dtoSaved);

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
    public ResponseEntity<Response<PrestadorDTO>> atualizarPrestador(@PathVariable("id") Long id, @Valid @RequestBody PrestadorDTO dto, BindingResult result) throws NotFoundException, InvalidUpdateException {

    	Response<PrestadorDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Prestador prestadorFind = prestadorService.findById(id);
		if (!prestadorFind.getId().equals(dto.getId()) ) {
			throw new InvalidUpdateException("Não foi possível alterar o id do cadastro = " + dto.getId());
		}
		
		if (!prestadorFind.getCpf().equals(dto.getCpf()) ) {
			throw new InvalidUpdateException("Não foi possível alterar o CPF do cadastro =" + dto.getCpf());
		}

		dto.setDataCadastro(prestadorFind.getDataCadastro());
		dto.setSituacao(prestadorFind.getSituacao());
		dto.setDataAtualizacao(new Date());
		Prestador prestadorUpdate = prestadorService.save(dto.convertDTOToEntity());
		
		PrestadorDTO itemDTO = prestadorUpdate.convertEntityToDTO();
		createSelfLink(prestadorUpdate, itemDTO);
		response.setData(itemDTO);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar o cadastro de um prestador")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Prestador inativado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição incorreta."),
            @ApiResponse(code = 404, message = "Prestador não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Response<String>> deletarPrestador(@PathVariable("id") Long id) throws NotFoundException{

    	Response<String> response = new Response<>();
		Prestador prestador = prestadorService.findById(id);
		
		prestadorService.deleteById(prestador.getId());
		response.setData("Prestador de id = " + prestador.getId() + " deletado com sucesso.");
		
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
    
    private void createSelfLink(Prestador prestador, PrestadorDTO prestadorDTO) {
		Link selfLink = WebMvcLinkBuilder.linkTo(PrestadoresController.class).slash(prestador.getId()).withSelfRel();
		prestadorDTO.add(selfLink);
	}
	
	private void createSelfLinkInCollections(final PrestadorDTO prestadorDTO)
			throws NotFoundException {
		Link selfLink = linkTo(methodOn(PrestadoresController.class)
				.listarPorId(prestadorDTO.getId()))
				.withSelfRel().expand();
		prestadorDTO.add(selfLink);
	
	}
}
