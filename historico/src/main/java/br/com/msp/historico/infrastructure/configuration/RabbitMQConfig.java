package br.com.msp.historico.infrastructure.configuration;

import br.com.msp.commons.config.RabbitConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange notificacoesExchange() {
        return new TopicExchange(RabbitConfig.EXCHANGE_NAME);
    }

    @Bean
    public Queue historicoQueue() {
        return new Queue(RabbitConfig.QUEUE_HISTORICO, true);
    }

    @Bean
    public Binding bindingHistorico(Queue historicoQueue, TopicExchange notificacoesExchange) {
        return BindingBuilder.bind(historicoQueue)
                .to(notificacoesExchange)
                .with(RabbitConfig.ROUTING_KEY_HISTORICO);
    }
}