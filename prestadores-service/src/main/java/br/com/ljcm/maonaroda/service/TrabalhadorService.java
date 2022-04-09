package br.com.ljcm.maonaroda.service;

import br.com.ljcm.maonaroda.exceptions.NotFoundException;
import br.com.ljcm.maonaroda.model.Trabalhador;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface TrabalhadorService {

    Trabalhador save(Trabalhador trabalhador);
    void deleteById(Long id);
    Page<Trabalhador> findAll(int page, int limit, String filter);
    Optional<Trabalhador> findByCpf(String cpf) throws NotFoundException;
    Trabalhador findById(Long id) throws NotFoundException;

}
