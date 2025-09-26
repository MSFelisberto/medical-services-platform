package br.com.msp.agendamento.infrastructure.producer;

import br.com.msp.agendamento.domain.model.Consulta;
import br.com.msp.agendamento.infrastructure.configuration.RabbitMQConfig;
import br.com.msp.agendamento.infrastructure.controllers.mappers.ConsultaDTOMapper;
import br.com.msp.medicalcommons.dtos.ConsultaDTO;
import br.com.msp.medicalcommons.enums.ETipoNotificacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    public NotificationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Consulta consulta, ETipoNotificacao tipo) {
        ConsultaDTO consultaDTO = ConsultaDTOMapper.toDTO(consulta);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                tipo.getRoutingKey(),
                consultaDTO
        );

        log.info("[NotificationProducer] Mensagem enviada: tipo={}, consulta={}",
                tipo, consultaDTO);
    }
}