package br.com.gisa.prestadores.service;

import br.com.gisa.prestadores.model.Prestador;
import br.com.gisa.prestadores.exceptions.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PrestadorService {

    Prestador save(Prestador associado);
    void deleteById(Long id);
    Page<Prestador> findAll(int page, int limit, String filter);
    Optional<Prestador> findByCpf(String cpf) throws NotFoundException;
    Prestador findById(Long id) throws NotFoundException;

}
