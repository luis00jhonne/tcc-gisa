package br.com.gisa.associados.service.impl;

import br.com.gisa.associados.dto.model.AutorizacaoExameConsultaDTO;
import br.com.gisa.associados.enums.SituacaoAutorizacaoEnum;
import br.com.gisa.associados.exceptions.NotFoundException;
import br.com.gisa.associados.messaging.Publisher;
import br.com.gisa.associados.model.Associado;
import br.com.gisa.associados.model.AutorizacaoExameConsulta;
import br.com.gisa.associados.repository.AutorizacaoRepository;
import br.com.gisa.associados.service.AssociadoService;
import br.com.gisa.associados.service.AutorizacaoService;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorizacaoServiceImpl implements AutorizacaoService {

    private AutorizacaoRepository autorizacaoRepository;

    private Publisher publisher;

    @Autowired
    public AutorizacaoServiceImpl(AutorizacaoRepository autorizacaoRepository, Publisher publisher){
        this.autorizacaoRepository = autorizacaoRepository;
        this.publisher = publisher;
    }

    @Override
    public AutorizacaoExameConsulta autorizarExameConsulta(AutorizacaoExameConsulta autorizacaoExameConsulta) {

        autorizacaoExameConsulta.setSituacao(SituacaoAutorizacaoEnum.SOLICITADO);
        autorizacaoExameConsulta.setDataSolicitacao(new Date());
        autorizacaoExameConsulta.setCodigoAutorizacao(UUID.randomUUID().toString());

        publisher.publishInQueue(autorizacaoExameConsulta);

        return save(autorizacaoExameConsulta);
    }

    @Override
    public AutorizacaoExameConsulta consultarExameConsulta(String codigoAutorizacao) throws NotFoundException {
        return autorizacaoRepository.findOneByCodigoAutorizacao(codigoAutorizacao)
                .orElseThrow(() -> new NotFoundException("Autorização não encontrada"));
    }

    @Override
    public void processarResultadoConsulta(String payload) {
        Gson gson = new Gson();
        AutorizacaoExameConsultaDTO autorizacaoExameConsultaDTO = gson.fromJson(payload, AutorizacaoExameConsultaDTO.class);
        autorizacaoRepository.save(autorizacaoExameConsultaDTO.convertDTOToEntity());
    }

    private AutorizacaoExameConsulta save(AutorizacaoExameConsulta associado){
        return autorizacaoRepository.save(associado);
    }

}
