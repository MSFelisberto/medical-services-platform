package br.com.msp.notificacoes.infrastructure.consumer;

import br.com.msp.notificacoes.application.usecases.SendNotificationUseCase;
import br.com.msp.notificacoes.application.usecases.SendNotificationUseCaseImpl;
import br.com.msp.notificacoes.infrastructure.configuration.RabbitMQConfiguration;
import br.com.msp.notificacoes.infrastructure.dtos.ConsultaDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private static final SendNotificationUseCase sendNotificationUseCase;

    @RabbitListener(queues = RabbitMQConfiguration.NOTIFICACAO_QUEUE)
    public void handleNewReservation(ConsultaDTO consulta) {
        this.sendNotificationUseCase.sendNotification(consulta);
    }

}
