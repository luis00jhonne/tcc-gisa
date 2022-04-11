package br.com.gisa.associados.service.impl;

import br.com.gisa.associados.enums.SituacaoAutorizacaoEnum;
import br.com.gisa.associados.exceptions.NotFoundException;
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

    private final AmqpTemplate queueSender;

    @Autowired
    public AutorizacaoServiceImpl(AutorizacaoRepository autorizacaoRepository, AmqpTemplate queueSender){
        this.autorizacaoRepository = autorizacaoRepository;
        this.queueSender = queueSender;
    }

    public AutorizacaoExameConsulta save(AutorizacaoExameConsulta associado){

        return autorizacaoRepository.save(associado);
    }

    @Override
    public AutorizacaoExameConsulta autorizarExameConsulta(AutorizacaoExameConsulta autorizacaoExameConsulta) {

        autorizacaoExameConsulta.setSituacao(SituacaoAutorizacaoEnum.SOLICITADO);
        autorizacaoExameConsulta.setDataAutorizacao(new Date());
        autorizacaoExameConsulta.setCodigoAutorizacao(UUID.randomUUID().toString());

        publishInQueue(autorizacaoExameConsulta);

        return save(autorizacaoExameConsulta);
    }

    @Override
    public AutorizacaoExameConsulta consultarExameConsulta(String codigoAutorizacao) throws NotFoundException {
        return autorizacaoRepository.findOneByCodigoAutorizacao(codigoAutorizacao)
                .orElseThrow(() -> new NotFoundException("Autorização não encontrada"));
    }

    public void publishInQueue(AutorizacaoExameConsulta autorizacaoExameConsulta){
        Gson gson = new Gson();
        queueSender.convertAndSend("gisa-exchange", "gisa-autorizacao-solicitacao",  gson.toJson(autorizacaoExameConsulta));
    }
}
