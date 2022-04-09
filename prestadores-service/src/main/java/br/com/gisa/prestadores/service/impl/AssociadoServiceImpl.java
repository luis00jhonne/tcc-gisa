package br.com.gisa.prestadores.service.impl;

import br.com.gisa.prestadores.model.Associado;
import br.com.gisa.prestadores.repository.AssociadoRepository;
import br.com.gisa.prestadores.service.AssociadoService;
import br.com.gisa.prestadores.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    private AssociadoRepository associadoRepository;

    @Autowired
    public AssociadoServiceImpl(AssociadoRepository associadoRepository){
        this.associadoRepository = associadoRepository;
    }

    public Page<Associado> findAll(int page, int limit, String filter){
        Pageable paging = PageRequest.of(page, limit);
        Page<Associado> pageResult;

        if (filter != null){
        	pageResult = associadoRepository.findByNome(filter, paging);
       } else {
        	pageResult = associadoRepository.findAll(paging);
       }

       return pageResult;
    }

    @Override
    public Optional<Associado> findByCpf(String cpf) throws NotFoundException {
        return associadoRepository.findOneByCpf(cpf);
    }

    @Override
    public Associado findById(Long id) throws NotFoundException {
        return associadoRepository.findById(
                id).orElseThrow(() -> new NotFoundException("Trabalhador com id = " + id + " nao encontrado"));
    }

    public Associado save(Associado associado){

        return associadoRepository.save(associado);
    }

    @Override
    public void deleteById(Long id) {
        associadoRepository.deleteById(id);
    }

}
