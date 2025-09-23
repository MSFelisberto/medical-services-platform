package br.com.msp.notificacoes.infrastructure.consumer;

import br.com.msp.notificacoes.application.usecases.ConsultaInput;
import br.com.msp.notificacoes.application.usecases.SendNotificationUseCase;
import br.com.msp.notificacoes.infrastructure.configuration.RabbitMQConfiguration;
import br.com.msp.notificacoes.infrastructure.dtos.ConsultaDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {
    private final SendNotificationUseCase sendNotificationUseCase;

    public NotificationConsumer(SendNotificationUseCase sendNotificationUseCase) {
        this.sendNotificationUseCase = sendNotificationUseCase;
    }

    @RabbitListener(queues = RabbitMQConfiguration.NOTIFICACAO_QUEUE)
    public void handleNewReservation(ConsultaDTO consulta) {
        this.sendNotificationUseCase.sendNotification(new ConsultaInput(
                consulta.pacienteId(),
                consulta.medicoId(),
                consulta.dataHora(),
                consulta.especialidade()
        ));
    }

}
