package br.com.gisa.acessoaolegado.messaging;

import br.com.gisa.acessoaolegado.dto.model.AutorizacaoExameConsultaDTO;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    @Value("${gisa.exchange.name}")
    private String exchangeName;

    @Value("${gisa.autorizacao.resultado.queue}")
    private String queueName;

    private final AmqpTemplate queueSender;

    @Autowired
    public Publisher(AmqpTemplate queueSender){
        this.queueSender = queueSender;
    }

    public void publishInQueue(AutorizacaoExameConsultaDTO autorizacaoExameConsulta){
        Gson gson = new Gson();
        queueSender.convertAndSend(exchangeName, queueName,  gson.toJson(autorizacaoExameConsulta));
    }

}
