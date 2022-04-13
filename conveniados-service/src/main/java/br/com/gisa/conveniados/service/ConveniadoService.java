package br.com.gisa.conveniados.service;

import br.com.gisa.conveniados.model.Conveniado;
import br.com.gisa.conveniados.exceptions.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ConveniadoService {

    Conveniado save(Conveniado conveniado);
    void deleteById(Long id);
    Page<Conveniado> findAll(int page, int limit, String filter);
    Optional<Conveniado> findByCpf(String cpf) throws NotFoundException;
    Conveniado findById(Long id) throws NotFoundException;

}
