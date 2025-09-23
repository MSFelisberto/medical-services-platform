package br.com.msp.notificacoes.infrastructure.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String EXCHANGE_NAME = "notificacoes";
    public static final String NOTIFICACAO_QUEUE = "notificacao_queue";
    public static final String ROUTING_KEY_NEW = "notificacao.new";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue notificacoesQueue() {
        return new Queue(NOTIFICACAO_QUEUE, true);
    }

    @Bean
    public Binding notificacoesBinding(DirectExchange directExchange, Queue notificacoesQueue) {
        return BindingBuilder.bind(notificacoesQueue).to(directExchange).with(ROUTING_KEY_NEW);
    }
}
