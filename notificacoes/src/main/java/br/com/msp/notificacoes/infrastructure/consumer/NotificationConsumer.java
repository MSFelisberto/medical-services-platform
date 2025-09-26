package br.com.msp.notificacoes.infrastructure.consumer;

import br.com.msp.commons.config.RabbitConfig;
import br.com.msp.commons.dtos.ConsultaDTO;
import br.com.msp.notificacoes.application.usecases.ConsultaInput;
import br.com.msp.notificacoes.application.usecases.SendNotificationUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationConsumer {
    private final SendNotificationUseCase sendNotificationUseCase;

    public NotificationConsumer(SendNotificationUseCase sendNotificationUseCase) {
        this.sendNotificationUseCase = sendNotificationUseCase;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_AGENDAR)
    public void consumeAgendar(ConsultaDTO consultaDTO) {
        log.info("[AGENDAR] Mensagem recebida: {}", consultaDTO);
        this.sendNotificationUseCase.sendNotification(new ConsultaInput(
                consultaDTO.pacienteId(),
                consultaDTO.medicoId(),
                consultaDTO.dataHora(),
                consultaDTO.especialidade()
        ));
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_CANCELAR)
    public void consumeCancelar(ConsultaDTO consultaDTO) {
        log.info("[CANCELAR] Mensagem recebida: {}", consultaDTO);
        this.sendNotificationUseCase.sendNotification(new ConsultaInput(
                consultaDTO.pacienteId(),
                consultaDTO.medicoId(),
                consultaDTO.dataHora(),
                consultaDTO.especialidade()
        ));
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_REAGENDAR)
    public void consumeReagendar(ConsultaDTO consultaDTO) {
        log.info("[REAGENDAR] Mensagem recebida: {}", consultaDTO);
        this.sendNotificationUseCase.sendNotification(new ConsultaInput(
                consultaDTO.pacienteId(),
                consultaDTO.medicoId(),
                consultaDTO.dataHora(),
                consultaDTO.especialidade()
        ));
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_HISTORICO)
    public void consumeHistorico(ConsultaDTO consultaDTO) {
        log.info("[HISTORICO] Mensagem recebida: {}", consultaDTO);
        this.sendNotificationUseCase.sendNotification(new ConsultaInput(
                consultaDTO.pacienteId(),
                consultaDTO.medicoId(),
                consultaDTO.dataHora(),
                consultaDTO.especialidade()
        ));
    }
}
