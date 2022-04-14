package br.com.gisa.associados.messaging;

import br.com.gisa.associados.model.AutorizacaoExameConsulta;
import br.com.gisa.associados.repository.AutorizacaoRepository;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    @Value("${gisa.exchange.name}")
    private String exchangeName;

    @Value("${gisa.autorizacao.solicitacao.queue}")
    private String queueName;

    private final AmqpTemplate queueSender;

    @Autowired
    public Publisher(AmqpTemplate queueSender){
        this.queueSender = queueSender;
    }

    public void publishInQueue(AutorizacaoExameConsulta autorizacaoExameConsulta){
        Gson gson = new Gson();
        queueSender.convertAndSend(exchangeName, queueName, gson.toJson(autorizacaoExameConsulta));
    }

}
