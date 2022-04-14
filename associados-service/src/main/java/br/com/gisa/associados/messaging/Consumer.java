package br.com.gisa.associados.messaging;

import br.com.gisa.associados.service.AutorizacaoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private final AutorizacaoService autorizacaoService;

    @Autowired
    public Consumer(AutorizacaoService autorizacaoService) {
        this.autorizacaoService = autorizacaoService;
    }

    @RabbitListener(queues = {"${gisa.autorizacao.resultado.queue}"})
    public void receive(@Payload String messageBody) {
        System.out.println("Processar Resultado Autorização " + messageBody);
        autorizacaoService.processarResultadoConsulta(messageBody);
    }
}
