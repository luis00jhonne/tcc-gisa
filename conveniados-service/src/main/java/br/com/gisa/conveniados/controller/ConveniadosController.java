package br.com.gisa.conveniados.controller;

import br.com.gisa.conveniados.exceptions.CPFExistenteException;
import br.com.gisa.conveniados.exceptions.InvalidUpdateException;
import br.com.gisa.conveniados.exceptions.NotFoundException;
import br.com.gisa.conveniados.model.Conveniado;
import br.com.gisa.conveniados.enums.SituacaoConveniadoEnum;
import br.com.gisa.conveniados.dto.model.ConveniadoDTO;
import br.com.gisa.conveniados.dto.response.Response;
import br.com.gisa.conveniados.service.ConveniadoService;
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
@RequestMapping("/v1/conveniados")
public class ConveniadosController {

	private ConveniadoService conveniadoService;

    @Autowired
    public ConveniadosController(ConveniadoService conveniadoService){
        this.conveniadoService = conveniadoService;
    }

    @ApiOperation(value = "Listar todos os conveniados")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma lista com conveniados, considerando os filtros"),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping
    public ResponseEntity<Response<List<ConveniadoDTO>>> listarTodos(
            @RequestParam(required = false) String filterName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit){
    	
    	Response<List<ConveniadoDTO>> response = new Response<>();
    	
    	Page<Conveniado> list = conveniadoService.findAll(page, limit, filterName);
		
		List<ConveniadoDTO> itemsDTO = new ArrayList<>();
		if (list.hasContent()) {
			list.stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
			itemsDTO.stream().forEach(dto -> {
				try {
					createSelfLinkInCollections(dto);
				} catch (NotFoundException e) {
					log.error("Nenhum conveniado cadastrado.");
				}
			});
		}
		
		response.setData(itemsDTO);
		response.setPage(list.getPageable());
		response.setTotalPages(list.getTotalPages());
		response.setTotalElements(list.getTotalElements());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Listar informações cadastrais de um conveniado")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna as informações cadastrais do conveniado especificado pelo id"),
            @ApiResponse(code = 404, message = "Conveniado não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<ConveniadoDTO>> listarPorId(@PathVariable Long id) throws NotFoundException {
    	
    	Response<ConveniadoDTO> response = new Response<>();
    	Conveniado conveniado = conveniadoService.findById(id);

		ConveniadoDTO dto = conveniado.convertEntityToDTO();

		createSelfLink(conveniado, dto);
    	response.setData(dto);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @ApiOperation(value = "Cadastrar um novo conveniado")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Conveniado cadastrado com sucesso"),
    		 @ApiResponse(code = 400, message = "Os dados da requisição estão incompletos ou incorretos."),
    		@ApiResponse(code = 204, message = "Erro ao cadastrar o conveniado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PostMapping
    public ResponseEntity<Response<ConveniadoDTO>> salvarConveniado(@Valid @RequestBody ConveniadoDTO dto, BindingResult result) {

    	Response<ConveniadoDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		dto.setSituacao(SituacaoConveniadoEnum.ATIVO);
		dto.setDataCadastro(new Date());
		Conveniado conveniadoToCreate = conveniadoService.save(dto.convertDTOToEntity());

		ConveniadoDTO dtoSaved = conveniadoToCreate.convertEntityToDTO();
		createSelfLink(conveniadoToCreate, dtoSaved);

		response.setData(dtoSaved);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar o cadastro do conveniado")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastro do conveniado atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Os dados da requisição estão incompletos ou incorretos.."),
            @ApiResponse(code = 404, message = "Conveniado não encontrado."),
            @ApiResponse(code = 409, message = "Tentativa de atualização inválida."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PutMapping(path="/{id}")
    public ResponseEntity<Response<ConveniadoDTO>> atualizarConveniado(@PathVariable("id") Long id, @Valid @RequestBody ConveniadoDTO dto, BindingResult result) throws NotFoundException, InvalidUpdateException {

    	Response<ConveniadoDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Conveniado conveniadoFind = conveniadoService.findById(id);
		if (!conveniadoFind.getId().equals(dto.getId()) ) {
			throw new InvalidUpdateException("Não foi possível alterar o id do cadastro = " + dto.getId());
		}
		
		if (!conveniadoFind.getCpf().equals(dto.getCpf()) ) {
			throw new InvalidUpdateException("Não foi possível alterar o CPF do cadastro =" + dto.getCpf());
		}

		dto.setDataAtualizacao(new Date());
		Conveniado conveniadoUpdate = conveniadoService.save(dto.convertDTOToEntity());
		
		ConveniadoDTO itemDTO = conveniadoUpdate.convertEntityToDTO();
		createSelfLink(conveniadoUpdate, itemDTO);
		response.setData(itemDTO);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Apagar o cadastro de um conveniado")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Conveniado inativado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição incorreta."),
            @ApiResponse(code = 404, message = "Conveniado não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Response<String>> inativarConveniado(@PathVariable("id") Long id) throws NotFoundException{

    	Response<String> response = new Response<>();
		Conveniado conveniado = conveniadoService.findById(id);
		
		conveniadoService.deleteById(conveniado.getId());
		response.setData("Conveniado de id = " + conveniado.getId() + " deletado com sucesso.");
		
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
    
    private void createSelfLink(Conveniado conveniado, ConveniadoDTO conveniadoDTO) {
		Link selfLink = WebMvcLinkBuilder.linkTo(ConveniadosController.class).slash(conveniado.getId()).withSelfRel();
		conveniadoDTO.add(selfLink);
	}
	
	private void createSelfLinkInCollections(final ConveniadoDTO conveniadoDTO)
			throws NotFoundException {
		Link selfLink = linkTo(methodOn(ConveniadosController.class)
				.listarPorId(conveniadoDTO.getId()))
				.withSelfRel().expand();
		conveniadoDTO.add(selfLink);
	
	}
}
