package br.com.gisa.prestadores.service;

import br.com.gisa.prestadores.model.Associado;
import br.com.gisa.prestadores.exceptions.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AssociadoService {

    Associado save(Associado associado);
    void deleteById(Long id);
    Page<Associado> findAll(int page, int limit, String filter);
    Optional<Associado> findByCpf(String cpf) throws NotFoundException;
    Associado findById(Long id) throws NotFoundException;

}
