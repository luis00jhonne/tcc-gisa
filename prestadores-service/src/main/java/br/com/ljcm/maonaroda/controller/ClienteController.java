package br.com.ljcm.maonaroda.controller;


import br.com.ljcm.maonaroda.dto.model.ClienteDTO;
import br.com.ljcm.maonaroda.dto.response.Response;
import br.com.ljcm.maonaroda.enums.SituacaoCadastroEnum;
import br.com.ljcm.maonaroda.exceptions.CPFExistenteException;
import br.com.ljcm.maonaroda.exceptions.InvalidUpdateException;
import br.com.ljcm.maonaroda.exceptions.NotFoundException;
import br.com.ljcm.maonaroda.model.Cliente;
import br.com.ljcm.maonaroda.service.ClienteService;
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
@RequestMapping("/mao-na-roda/v1/cliente")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @ApiOperation(value = "Lista de Clientes")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de clientes"),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping
    public ResponseEntity<Response<List<ClienteDTO>>> listAll(
            @RequestParam(required = false) String filterName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit){

        Response<List<ClienteDTO>> response = new Response<>();

        Page<Cliente> list = clienteService.findAll(page, limit, filterName);

        List<ClienteDTO> itemsDTO = new ArrayList<>();
        if (list.hasContent()) {
            list.stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
            itemsDTO.stream().forEach(dto -> {
                try {
                    createSelfLinkInCollections(dto);
                } catch (NotFoundException e) {
                    log.error("Nenhum cliente cadastrado.");
                }
            });
        }

        response.setData(itemsDTO);
        response.setPage(list.getPageable());
        response.setTotalPages(list.getTotalPages());
        response.setTotalElements(list.getTotalElements());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Listar um cliente pelo id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o cliente especificado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<ClienteDTO>> findById(@PathVariable Long id) throws NotFoundException {

        Response<ClienteDTO> response = new Response<>();
        Cliente cliente = clienteService.findById(id);

        ClienteDTO dto = cliente.convertEntityToDTO();

        createSelfLink(cliente, dto);
        response.setData(dto);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @ApiOperation(value = "Salvar o cadastro de um novo cliente")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição incorreta."),
            @ApiResponse(code = 204, message = "Cliente não cadastrado"),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PostMapping
    public ResponseEntity<Response<ClienteDTO>> salvarCliente(@Valid @RequestBody ClienteDTO dto, BindingResult result) throws NotFoundException, CPFExistenteException {

        Response<ClienteDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Cliente> vehicleToFind = clienteService.findByCpf(dto.getCpf());

        if(vehicleToFind.isPresent()) {
            throw new CPFExistenteException("CPF já cadastrado.");
        }

        dto.setSituacao(SituacaoCadastroEnum.PENDENTE);
        dto.setDataCadastro(new Date());
        Cliente clienteToCreate = clienteService.save(dto.convertDTOToEntity());

        ClienteDTO dtoSaved = clienteToCreate.convertEntityToDTO();
        createSelfLink(clienteToCreate, dtoSaved);

        response.setData(dtoSaved);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar o cadastro do cliente")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastro do cliente atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição incorreta."),
            @ApiResponse(code = 404, message = "Cliente não encontrado."),
            @ApiResponse(code = 409, message = "Tentativa de atualização inválida."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PutMapping(path="/{id}")
    public ResponseEntity<Response<ClienteDTO>> atualizarCliente(@PathVariable("id") Long id, @Valid @RequestBody ClienteDTO dto, BindingResult result) throws NotFoundException, InvalidUpdateException {

        Response<ClienteDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Cliente clienteFind = clienteService.findById(id);
        if (!clienteFind.getId().equals(dto.getId()) ) {
            throw new InvalidUpdateException("Não foi possível alterar o id do cadastro = " + dto.getId());
        }

        if (!clienteFind.getCpf().equals(dto.getCpf()) ) {
            throw new InvalidUpdateException("Não foi possível alterar o CPF do cadastro =" + dto.getCpf());
        }

        dto.setDataAtualizacao(new Date());
        Cliente clienteUpdate = clienteService.save(dto.convertDTOToEntity());

        ClienteDTO itemDTO = clienteUpdate.convertEntityToDTO();
        createSelfLink(clienteUpdate, itemDTO);
        response.setData(itemDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar cadastro de um cliente")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Cliente deletado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição incorreta."),
            @ApiResponse(code = 404, message = "Cliente não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Response<String>> deletarCliente(@PathVariable("id") Long id) throws NotFoundException{

        Response<String> response = new Response<>();
        Cliente cliente = clienteService.findById(id);

        clienteService.deleteById(cliente.getId());
        response.setData("Cliente de id = " + cliente.getId() + " deletado com sucesso.");

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    private void createSelfLink(Cliente cliente, ClienteDTO clienteDTO) {
        Link selfLink = WebMvcLinkBuilder.linkTo(ClienteController.class).slash(cliente.getId()).withSelfRel();
        clienteDTO.add(selfLink);
    }

    private void createSelfLinkInCollections(final ClienteDTO clienteDTO)
            throws NotFoundException {
        Link selfLink = linkTo(methodOn(ClienteController.class)
                .findById(clienteDTO.getId()))
                .withSelfRel().expand();
        clienteDTO.add(selfLink);

    }
}
