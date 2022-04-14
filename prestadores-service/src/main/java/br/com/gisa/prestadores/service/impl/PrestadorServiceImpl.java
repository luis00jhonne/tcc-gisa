package br.com.gisa.prestadores.service.impl;

import br.com.gisa.prestadores.model.Prestador;
import br.com.gisa.prestadores.repository.PrestadorRepository;
import br.com.gisa.prestadores.service.PrestadorService;
import br.com.gisa.prestadores.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrestadorServiceImpl implements PrestadorService {

    private PrestadorRepository prestadorRepository;

    @Autowired
    public PrestadorServiceImpl(PrestadorRepository prestadorRepository){
        this.prestadorRepository = prestadorRepository;
    }

    public Page<Prestador> findAll(int page, int limit, String filter){
        Pageable paging = PageRequest.of(page, limit);
        Page<Prestador> pageResult;

        if (filter != null){
        	pageResult = prestadorRepository.findByNome(filter, paging);
       } else {
        	pageResult = prestadorRepository.findAll(paging);
       }

       return pageResult;
    }

    @Override
    public Optional<Prestador> findByCpf(String cpf) throws NotFoundException {
        return prestadorRepository.findOneByCpf(cpf);
    }

    @Override
    public Prestador findById(Long id) throws NotFoundException {
        return prestadorRepository.findById(
                id).orElseThrow(() -> new NotFoundException("Prestador com id = " + id + " nao encontrado"));
    }

    public Prestador save(Prestador prestador){

        return prestadorRepository.save(prestador);
    }

    @Override
    public void deleteById(Long id) {
        prestadorRepository.deleteById(id);
    }

}
