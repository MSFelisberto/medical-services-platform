package br.com.msp.agendamento.infrastructure.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "notificacoes";

    public static final String QUEUE_AGENDAR = "notificacao.agendar.queue";
    public static final String QUEUE_CANCELAR = "notificacao.cancelar.queue";
    public static final String QUEUE_REAGENDAR = "notificacao.reagendar.queue";

    public static final String ROUTING_KEY_AGENDAR = "notificacao.agendar";
    public static final String ROUTING_KEY_CANCELAR = "notificacao.cancelar";
    public static final String ROUTING_KEY_REAGENDAR = "notificacao.reagendar";

    @Bean
    public TopicExchange notificacoesExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue agendarQueue() {
        return new Queue(QUEUE_AGENDAR, true);
    }

    @Bean
    public Queue cancelarQueue() {
        return new Queue(QUEUE_CANCELAR, true);
    }

    @Bean
    public Queue reagendarQueue() {
        return new Queue(QUEUE_REAGENDAR, true);
    }

    @Bean
    public Binding bindingAgendar(Queue agendarQueue, TopicExchange notificacoesExchange) {
        return BindingBuilder.bind(agendarQueue).to(notificacoesExchange).with(ROUTING_KEY_AGENDAR);
    }

    @Bean
    public Binding bindingCancelar(Queue cancelarQueue, TopicExchange notificacoesExchange) {
        return BindingBuilder.bind(cancelarQueue).to(notificacoesExchange).with(ROUTING_KEY_CANCELAR);
    }

    @Bean
    public Binding bindingReagendar(Queue reagendarQueue, TopicExchange notificacoesExchange) {
        return BindingBuilder.bind(reagendarQueue).to(notificacoesExchange).with(ROUTING_KEY_REAGENDAR);
    }
}
