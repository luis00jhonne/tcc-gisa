package br.com.ljcm.maonaroda.controller;

import br.com.ljcm.maonaroda.enums.SituacaoCadastroEnum;
import br.com.ljcm.maonaroda.dto.model.TrabalhadorDTO;
import br.com.ljcm.maonaroda.dto.response.Response;
import br.com.ljcm.maonaroda.exceptions.*;
import br.com.ljcm.maonaroda.model.Trabalhador;
import br.com.ljcm.maonaroda.service.TrabalhadorService;
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
@RequestMapping("/mao-na-roda/v1/trabalhador")
public class TrabalhadorController {

	private TrabalhadorService trabalhadorService;

    @Autowired
    public TrabalhadorController(TrabalhadorService trabalhadorService){
        this.trabalhadorService = trabalhadorService;
    }

    @ApiOperation(value = "Lista de Trabalhadores")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de trabalhadores"),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping
    public ResponseEntity<Response<List<TrabalhadorDTO>>> listAll(
            @RequestParam(required = false) String filterName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit){
    	
    	Response<List<TrabalhadorDTO>> response = new Response<>();
    	
    	Page<Trabalhador> list = trabalhadorService.findAll(page, limit, filterName);
		
		List<TrabalhadorDTO> itemsDTO = new ArrayList<>();
		if (list.hasContent()) {
			list.stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
			itemsDTO.stream().forEach(dto -> {
				try {
					createSelfLinkInCollections(dto);
				} catch (NotFoundException e) {
					log.error("Nenhum trabalhador cadastrado.");
				}
			});
		}
		
		response.setData(itemsDTO);
		response.setPage(list.getPageable());
		response.setTotalPages(list.getTotalPages());
		response.setTotalElements(list.getTotalElements());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Listar um trabalhador pelo id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o trabalhador especificado"),
            @ApiResponse(code = 404, message = "Trabalhador não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<TrabalhadorDTO>> findById(@PathVariable Long id) throws NotFoundException {
    	
    	Response<TrabalhadorDTO> response = new Response<>();
    	Trabalhador trabalhador = trabalhadorService.findById(id);

		TrabalhadorDTO dto = trabalhador.convertEntityToDTO();
    	
    	createSelfLink(trabalhador, dto);
    	response.setData(dto);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @ApiOperation(value = "Salvar o cadastro de um novo trabalhador")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Trabalhador cadastrado com sucesso"),
    		 @ApiResponse(code = 400, message = "Requisição incorreta."),
    		@ApiResponse(code = 204, message = "Trabalhador não cadastrado"),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PostMapping
    public ResponseEntity<Response<TrabalhadorDTO>> salvarTrabalhador(@Valid @RequestBody TrabalhadorDTO dto,  BindingResult result) throws NotFoundException, CPFExistenteException {

    	Response<TrabalhadorDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Optional<Trabalhador> vehicleToFind = trabalhadorService.findByCpf(dto.getCpf());
		
		if(vehicleToFind.isPresent()) {
			throw new CPFExistenteException("CPF já cadastrado.");
		}

		dto.setSituacao(SituacaoCadastroEnum.PENDENTE);
		dto.setDataCadastro(new Date());
		Trabalhador trabalhadorToCreate = trabalhadorService.save(dto.convertDTOToEntity());

		TrabalhadorDTO dtoSaved = trabalhadorToCreate.convertEntityToDTO();
		createSelfLink(trabalhadorToCreate, dtoSaved);

		response.setData(dtoSaved);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar o cadastro do trabalhador")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastro do trabalhador atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição incorreta."),
            @ApiResponse(code = 404, message = "Trabalhador não encontrado."),
            @ApiResponse(code = 409, message = "Tentativa de atualização inválida."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PutMapping(path="/{id}")
    public ResponseEntity<Response<TrabalhadorDTO>> atualizarTrabalhador(@PathVariable("id") Long id, @Valid @RequestBody TrabalhadorDTO dto, BindingResult result) throws NotFoundException, InvalidUpdateException {

    	Response<TrabalhadorDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Trabalhador trabalhadorFind = trabalhadorService.findById(id);
		if (!trabalhadorFind.getId().equals(dto.getId()) ) {
			throw new InvalidUpdateException("Não foi possível alterar o id do cadastro = " + dto.getId());
		}
		
		if (!trabalhadorFind.getCpf().equals(dto.getCpf()) ) {
			throw new InvalidUpdateException("Não foi possível alterar o CPF do cadastro =" + dto.getCpf());
		}

		dto.setDataAtualizacao(new Date());
		Trabalhador trabalhadorUpdate = trabalhadorService.save(dto.convertDTOToEntity());
		
		TrabalhadorDTO itemDTO = trabalhadorUpdate.convertEntityToDTO();
		createSelfLink(trabalhadorUpdate, itemDTO);
		response.setData(itemDTO);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar cadastro de um trabalhador")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Trabalhador deletado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição incorreta."),
            @ApiResponse(code = 404, message = "Trabalhador não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Response<String>> deletarTrabalhador(@PathVariable("id") Long id) throws NotFoundException{

    	Response<String> response = new Response<>();
		Trabalhador trabalhador = trabalhadorService.findById(id);
		
		trabalhadorService.deleteById(trabalhador.getId());
		response.setData("Trabalhador de id = " + trabalhador.getId() + " deletado com sucesso.");
		
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
    
    private void createSelfLink(Trabalhador trabalhador, TrabalhadorDTO trabalhadorDTO) {
		Link selfLink = WebMvcLinkBuilder.linkTo(TrabalhadorController.class).slash(trabalhador.getId()).withSelfRel();
		trabalhadorDTO.add(selfLink);
	}
	
	private void createSelfLinkInCollections(final TrabalhadorDTO trabalhadorDTO)
			throws NotFoundException {
		Link selfLink = linkTo(methodOn(TrabalhadorController.class)
				.findById(trabalhadorDTO.getId()))
				.withSelfRel().expand();
		trabalhadorDTO.add(selfLink);
	
	}
}
