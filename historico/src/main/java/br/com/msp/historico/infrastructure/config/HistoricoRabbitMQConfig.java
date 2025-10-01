package br.com.msp.historico.infrastructure.config;

import br.com.msp.commons.config.RabbitConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HistoricoRabbitMQConfig {

    @Bean
    public TopicExchange notificacoesExchange() {
        return new TopicExchange(RabbitConfig.EXCHANGE_NAME);
    }

    @Bean
    public Queue historicoAgendarQueue() {
        return new Queue(RabbitConfig.QUEUE_HISTORICO_AGENDAR, true);
    }

    @Bean
    public Queue historicoCancelarQueue() {
        return new Queue(RabbitConfig.QUEUE_HISTORICO_CANCELAR, true);
    }

    @Bean
    public Queue historicoReagendarQueue() {
        return new Queue(RabbitConfig.QUEUE_HISTORICO_REAGENDAR, true);
    }

    @Bean
    public Binding bindingHistoricoAgendar(Queue historicoAgendarQueue, TopicExchange notificacoesExchange) {
        return BindingBuilder.bind(historicoAgendarQueue).to(notificacoesExchange).with(RabbitConfig.ROUTING_KEY_AGENDAR);
    }

    @Bean
    public Binding bindingHistoricoCancelar(Queue historicoCancelarQueue, TopicExchange notificacoesExchange) {
        return BindingBuilder.bind(historicoCancelarQueue).to(notificacoesExchange).with(RabbitConfig.ROUTING_KEY_CANCELAR);
    }

    @Bean
    public Binding bindingHistoricoReagendar(Queue historicoReagendarQueue, TopicExchange notificacoesExchange) {
        return BindingBuilder.bind(historicoReagendarQueue).to(notificacoesExchange).with(RabbitConfig.ROUTING_KEY_REAGENDAR);
    }
}
