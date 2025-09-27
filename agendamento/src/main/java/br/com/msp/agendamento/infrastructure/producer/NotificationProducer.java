package br.com.msp.agendamento.infrastructure.producer;

import br.com.msp.agendamento.domain.model.Consulta;
import br.com.msp.agendamento.infrastructure.controllers.mappers.ConsultaDTOMapper;
import br.com.msp.commons.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationProducer {
    public static final String EXCHANGE_NAME = "notificacoes";
    public static final String ROUTING_KEY_NEW = "notificacao.new";
    private final RabbitTemplate rabbitTemplate;

    public NotificationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendAgendar(Consulta consulta) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_NAME,
                RabbitConfig.ROUTING_KEY_AGENDAR,
                ConsultaDTOMapper.toDTO(consulta)
        );
        log.info("Mensagem enviada para AGENDAR");
    }

    public void sendCancelar(Consulta consulta) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_NAME,
                RabbitConfig.ROUTING_KEY_CANCELAR,
                ConsultaDTOMapper.toDTO(consulta)
        );
        log.info("Mensagem enviada para CANCELAR");
    }

    public void sendReagendar(Consulta consulta) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_NAME,
                RabbitConfig.ROUTING_KEY_REAGENDAR,
                ConsultaDTOMapper.toDTO(consulta)
        );
        log.info("Mensagem enviada para REAGENDAR");
    }
}