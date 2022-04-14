package br.com.gisa.acessoaolegado.messaging;

import br.com.gisa.acessoaolegado.service.AcessoSGPSService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private final AcessoSGPSService acessoSGPSService;

    @Autowired
    public Consumer(AcessoSGPSService acessoSGPSService) {
        this.acessoSGPSService = acessoSGPSService;
    }

    @RabbitListener(queues = {"${gisa.autorizacao.solicitacao.queue}"})
    public void receive(@Payload String messageBody) {
        System.out.println("Message " + messageBody);
        acessoSGPSService.processarAutorizacao(messageBody);
    }
}
