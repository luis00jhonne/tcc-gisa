package br.com.ljcm.maonaroda.service.impl;

import br.com.ljcm.maonaroda.exceptions.NotFoundException;
import br.com.ljcm.maonaroda.model.Trabalhador;
import br.com.ljcm.maonaroda.repository.TrabalhadorRepository;
import br.com.ljcm.maonaroda.service.TrabalhadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrabalhadorServiceImpl implements TrabalhadorService {

    private TrabalhadorRepository trabalhadorRepository;

    @Autowired
    public TrabalhadorServiceImpl(TrabalhadorRepository trabalhadorRepository){
        this.trabalhadorRepository = trabalhadorRepository;
    }

    public Page<Trabalhador> findAll(int page, int limit, String filter){
        Pageable paging = PageRequest.of(page, limit);
        Page<Trabalhador> pageResult;

        if (filter != null){
        	pageResult = trabalhadorRepository.findByNome(filter, paging);
       } else {
        	pageResult = trabalhadorRepository.findAll(paging);
       }

       return pageResult;
    }

    @Override
    public Optional<Trabalhador> findByCpf(String cpf) throws NotFoundException {
        return trabalhadorRepository.findOneByCpf(cpf);
    }

    @Override
    public Trabalhador findById(Long id) throws NotFoundException {
        return trabalhadorRepository.findById(
                id).orElseThrow(() -> new NotFoundException("Trabalhador com id = " + id + " nao encontrado"));
    }

    public Trabalhador save(Trabalhador trabalhador){

        return trabalhadorRepository.save(trabalhador);
    }

    @Override
    public void deleteById(Long id) {
        trabalhadorRepository.deleteById(id);
    }

}
