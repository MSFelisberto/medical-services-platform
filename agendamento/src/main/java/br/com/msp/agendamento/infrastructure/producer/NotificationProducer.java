package br.com.msp.agendamento.infrastructure.producer;

import br.com.msp.agendamento.domain.model.Consulta;
import br.com.msp.agendamento.infrastructure.controllers.dto.ConsultaDTO;
import br.com.msp.agendamento.infrastructure.controllers.mappers.ConsultaDTOMapper;
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

    public void send(Consulta consulta) {
        ConsultaDTO consultaDTO = ConsultaDTOMapper.toDTO(consulta);

        log.info("[NotificationProducer] Enviando publish");
        rabbitTemplate.convertAndSend(
                EXCHANGE_NAME,
                ROUTING_KEY_NEW,
                consultaDTO
        );
    }
}