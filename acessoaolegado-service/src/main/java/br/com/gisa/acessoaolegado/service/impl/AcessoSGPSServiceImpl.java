package br.com.gisa.acessoaolegado.service.impl;

import br.com.gisa.acessoaolegado.dto.model.AutorizacaoExameConsultaDTO;
import br.com.gisa.acessoaolegado.enums.SituacaoAutorizacaoEnum;
import br.com.gisa.acessoaolegado.messaging.Publisher;
import br.com.gisa.acessoaolegado.service.AcessoSGPSService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AcessoSGPSServiceImpl implements AcessoSGPSService {

    private Publisher publisher;

    @Autowired
    public AcessoSGPSServiceImpl(Publisher publisher){
        this.publisher = publisher;
    }

    public void processarAutorizacao(String payload){

        Gson gson = new Gson();
        AutorizacaoExameConsultaDTO dto = gson.fromJson(payload, AutorizacaoExameConsultaDTO.class);
        dto.setDataAutorizacao(new Date());
        dto.setSituacao(SituacaoAutorizacaoEnum.AUTORIZADO);

        publisher.publishInQueue(dto);

    }
}
