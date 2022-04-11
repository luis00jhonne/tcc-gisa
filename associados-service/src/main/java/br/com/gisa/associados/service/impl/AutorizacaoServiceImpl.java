package br.com.gisa.associados.service.impl;

import br.com.gisa.associados.enums.SituacaoAutorizacaoEnum;
import br.com.gisa.associados.exceptions.NotFoundException;
import br.com.gisa.associados.model.AutorizacaoExameConsulta;
import br.com.gisa.associados.repository.AutorizacaoRepository;
import br.com.gisa.associados.service.AutorizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AutorizacaoServiceImpl implements AutorizacaoService {

    private AutorizacaoRepository autorizacaoRepository;

    @Autowired
    public AutorizacaoServiceImpl(AutorizacaoRepository autorizacaoRepository){
        this.autorizacaoRepository = autorizacaoRepository;
    }

    public AutorizacaoExameConsulta save(AutorizacaoExameConsulta associado){

        return autorizacaoRepository.save(associado);
    }

    @Override
    public AutorizacaoExameConsulta autorizarExameConsulta(AutorizacaoExameConsulta autorizacaoExameConsulta) {

        autorizacaoExameConsulta.setSituacao(SituacaoAutorizacaoEnum.SOLICITADO);
        autorizacaoExameConsulta.setDataAutorizacao(new Date());
        autorizacaoExameConsulta.setCodigoAutorizacao(UUID.randomUUID().toString());

        return save(autorizacaoExameConsulta);
    }

    @Override
    public AutorizacaoExameConsulta consultarExameConsulta(String codigoAutorizacao) throws NotFoundException {
        return autorizacaoRepository.findOneByCodigoAutorizacao(codigoAutorizacao)
                .orElseThrow(() -> new NotFoundException("Autorização não encontrada"));
    }
}
