package br.com.msp.notificacoes.infrastructure.producer;

import br.com.msp.notificacoes.infrastructure.configuration.RabbitMQConfiguration;
import br.com.msp.notificacoes.infrastructure.dtos.ConsultaDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    public NotificationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(ConsultaDTO consultaDTO) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfiguration.EXCHANGE_NAME,
                RabbitMQConfiguration.ROUTING_KEY_NEW,
                consultaDTO
        );
    }
}