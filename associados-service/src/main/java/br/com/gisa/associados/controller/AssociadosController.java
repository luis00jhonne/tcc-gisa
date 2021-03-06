package br.com.gisa.associados.controller;

import br.com.gisa.associados.exceptions.CPFExistenteException;
import br.com.gisa.associados.exceptions.InvalidUpdateException;
import br.com.gisa.associados.exceptions.NotFoundException;
import br.com.gisa.associados.model.Associado;
import br.com.gisa.associados.enums.SituacaoAssociadoEnum;
import br.com.gisa.associados.dto.model.AssociadoDTO;
import br.com.gisa.associados.dto.response.Response;
import br.com.gisa.associados.service.AssociadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
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
@RequestMapping("/v1/associados")
public class AssociadosController {

	private AssociadoService associadoService;

    @Autowired
    public AssociadosController(AssociadoService associadoService){
        this.associadoService = associadoService;
    }

    @ApiOperation(value = "Listar todos os associados")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma lista com associados, considerando o filtro de nome e parâmetros de paginação"),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping
    public ResponseEntity<Response<List<AssociadoDTO>>> listarTodos(
            @RequestParam(required = false) String filterName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit){
    	
    	Response<List<AssociadoDTO>> response = new Response<>();
    	
    	Page<Associado> list = associadoService.findAll(page, limit, filterName);

		List<AssociadoDTO> itemsDTO = getAssociadoDTOS(list);

		response.setData(itemsDTO);
		response.setPage(list.getPageable());
		response.setTotalPages(list.getTotalPages());
		response.setTotalElements(list.getTotalElements());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@ApiOperation(value = "Listar informações cadastrais e histórico de saúde de um associado")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna as informações cadastrais do associado especificado pelo id"),
            @ApiResponse(code = 404, message = "Associado não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<AssociadoDTO>> listarPorId(@PathVariable Long id) throws NotFoundException {
    	
    	Response<AssociadoDTO> response = new Response<>();
    	Associado trabalhador = associadoService.findById(id);

		AssociadoDTO dto = trabalhador.convertEntityToDTO();

    	createSelfLink(trabalhador, dto);
    	response.setData(dto);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @ApiOperation(value = "Cadastrar um novo associado")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Associado cadastrado com sucesso"),
    		 @ApiResponse(code = 400, message = "Os dados da requisição estão incompletos ou incorretos."),
    		@ApiResponse(code = 204, message = "Erro ao cadastrar o associado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PostMapping
    public ResponseEntity<Response<AssociadoDTO>> salvarAssociado(@Valid @RequestBody AssociadoDTO dto,  BindingResult result) throws NotFoundException, CPFExistenteException {

    	Response<AssociadoDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Optional<Associado> associadoEncontrado = associadoService.findByCpf(dto.getCpf());
		
		if(associadoEncontrado.isPresent()) {
			throw new CPFExistenteException("CPF já cadastrado.");
		}

		dto.setSituacao(SituacaoAssociadoEnum.SUSPENSO);
		dto.setDataCadastro(new Date());
		Associado trabalhadorToCreate = associadoService.save(dto.convertDTOToEntity());

		AssociadoDTO dtoSaved = trabalhadorToCreate.convertEntityToDTO();
		createSelfLink(trabalhadorToCreate, dtoSaved);

		response.setData(dtoSaved);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar o cadastro do associado")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastro do associado atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Os dados da requisição estão incompletos ou incorretos."),
            @ApiResponse(code = 404, message = "Associado não encontrado."),
            @ApiResponse(code = 409, message = "Tentativa de atualização inválida."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PutMapping(path="/{id}")
    public ResponseEntity<Response<AssociadoDTO>> atualizarAssociado(@PathVariable("id") Long id, @Valid @RequestBody AssociadoDTO dto, BindingResult result) throws NotFoundException, InvalidUpdateException {

    	Response<AssociadoDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Associado associadoFind = associadoService.findById(id);
		if (!associadoFind.getId().equals(dto.getId()) ) {
			throw new InvalidUpdateException("Não foi possível alterar o id do cadastro = " + dto.getId());
		}
		
		if (!associadoFind.getCpf().equals(dto.getCpf()) ) {
			throw new InvalidUpdateException("Não foi possível alterar o CPF do cadastro =" + dto.getCpf());
		}

		dto.setDataCadastro(associadoFind.getDataCadastro());
		dto.setSituacao(associadoFind.getSituacao());
		dto.setDataAtualizacao(new Date());

		Associado associadoUpdate = associadoService.save(dto.convertDTOToEntity());
		
		AssociadoDTO itemDTO = associadoUpdate.convertEntityToDTO();
		createSelfLink(associadoUpdate, itemDTO);
		response.setData(itemDTO);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Inativar o cadastro de um associado")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Associado inativado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição incorreta."),
            @ApiResponse(code = 404, message = "Associado não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Response<String>> inativarAssociado(@PathVariable("id") Long id) throws NotFoundException{

    	Response<String> response = new Response<>();
		Associado associado = associadoService.findById(id);
		associado.setSituacao(SituacaoAssociadoEnum.INATIVO);

		associadoService.save(associado);
		response.setData("Associado de id = " + associado.getId() + " inativado com sucesso.");
		
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

	@NotNull
	private List<AssociadoDTO> getAssociadoDTOS(Page<Associado> list) {
		List<AssociadoDTO> itemsDTO = new ArrayList<>();
		if (list.hasContent()) {
			list.stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
			itemsDTO.stream().forEach(dto -> {
				try {
					createSelfLinkInCollections(dto);
				} catch (NotFoundException e) {
					log.error("Nenhum associado cadastrado.");
				}
			});
		}
		return itemsDTO;
	}
    
    private void createSelfLink(Associado associado, AssociadoDTO associadoDTO) {
		Link selfLink = WebMvcLinkBuilder.linkTo(AssociadosController.class).slash(associado.getId()).withSelfRel();
		associadoDTO.add(selfLink);
	}
	
	private void createSelfLinkInCollections(final AssociadoDTO trabalhadorDTO)
			throws NotFoundException {
		Link selfLink = linkTo(methodOn(AssociadosController.class)
				.listarPorId(trabalhadorDTO.getId()))
				.withSelfRel().expand();
		trabalhadorDTO.add(selfLink);
	
	}
}
