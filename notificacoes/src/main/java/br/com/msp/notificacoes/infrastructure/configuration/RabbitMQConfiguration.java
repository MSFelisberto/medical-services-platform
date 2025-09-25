package br.com.msp.notificacoes.infrastructure.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
