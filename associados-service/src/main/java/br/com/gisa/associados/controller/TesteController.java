package br.com.gisa.associados.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

    private final AmqpTemplate queueSender;

    public TesteController(AmqpTemplate queueSender) {
        this.queueSender = queueSender;
    }


    @GetMapping
    public String send(){
        queueSender.convertAndSend("direct-exchange", "associados-service", "test message");
        return "ok. done";
    }

}