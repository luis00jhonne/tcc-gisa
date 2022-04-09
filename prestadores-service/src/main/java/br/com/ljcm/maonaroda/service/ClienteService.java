package br.com.ljcm.maonaroda.service;

import br.com.ljcm.maonaroda.exceptions.NotFoundException;
import br.com.ljcm.maonaroda.model.Cliente;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ClienteService {

    Cliente save(Cliente Cliente);
    void deleteById(Long id);
    Page<Cliente> findAll(int page, int limit, String filter);
    Optional<Cliente> findByCpf(String cpf) throws NotFoundException;
    Cliente findById(Long id) throws NotFoundException;

}
