package br.com.gisa.conveniados.service.impl;

import br.com.gisa.conveniados.model.Conveniado;
import br.com.gisa.conveniados.repository.ConveniadoRepository;
import br.com.gisa.conveniados.service.ConveniadoService;
import br.com.gisa.conveniados.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConveniadoServiceImpl implements ConveniadoService {

    private ConveniadoRepository conveniadoRepository;

    @Autowired
    public ConveniadoServiceImpl(ConveniadoRepository conveniadoRepository){
        this.conveniadoRepository = conveniadoRepository;
    }

    public Page<Conveniado> findAll(int page, int limit, String filter){
        Pageable paging = PageRequest.of(page, limit);
        Page<Conveniado> pageResult;

        if (filter != null){
        	pageResult = conveniadoRepository.findByNome(filter, paging);
       } else {
        	pageResult = conveniadoRepository.findAll(paging);
       }

       return pageResult;
    }

    @Override
    public Optional<Conveniado> findByCpf(String cpf) throws NotFoundException {
        return conveniadoRepository.findOneByCpf(cpf);
    }

    @Override
    public Conveniado findById(Long id) throws NotFoundException {
        return conveniadoRepository.findById(
                id).orElseThrow(() -> new NotFoundException("Conveniado com id = " + id + " nao encontrado"));
    }

    public Conveniado save(Conveniado conveniado){

        return conveniadoRepository.save(conveniado);
    }

    @Override
    public void deleteById(Long id) {
        conveniadoRepository.deleteById(id);
    }

}
