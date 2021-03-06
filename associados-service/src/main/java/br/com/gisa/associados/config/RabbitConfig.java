package br.com.gisa.associados.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Value("${gisa.exchange.name}")
    private String exchangeName;

    @Value("${gisa.autorizacao.solicitacao.queue}")
    private String queueName;

    @Value("${gisa.autorizacao.solicitacao.routingKey}")
    private String routingKey;

    @Bean()
    Queue queue() {
        return new Queue(queueName, true);
    }


    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    Binding bindingQueueSolicitacao(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
}
