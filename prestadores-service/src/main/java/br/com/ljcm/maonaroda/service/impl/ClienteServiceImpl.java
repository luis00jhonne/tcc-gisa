package br.com.ljcm.maonaroda.service.impl;

import br.com.ljcm.maonaroda.exceptions.NotFoundException;
import br.com.ljcm.maonaroda.model.Cliente;
import br.com.ljcm.maonaroda.repository.ClienteRepository;
import br.com.ljcm.maonaroda.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Page<Cliente> findAll(int page, int limit, String filter){
        Pageable paging = PageRequest.of(page, limit);
        Page<Cliente> pageResult;

        if (filter != null){
        	pageResult = clienteRepository.findByNome(filter, paging);
       } else {
        	pageResult = clienteRepository.findAll(paging);
       }

       return pageResult;
    }

    @Override
    public Optional<Cliente> findByCpf(String cpf) throws NotFoundException {
        return clienteRepository.findOneByCpf(cpf);
    }

    @Override
    public Cliente findById(Long id) throws NotFoundException {
        return clienteRepository.findById(
                id).orElseThrow(() -> new NotFoundException("Cliente com id = " + id + " nao encontrado"));
    }

    public Cliente save(Cliente cliente){

        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

}
