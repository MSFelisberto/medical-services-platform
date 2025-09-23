package br.com.msp.notificacoes.infrastructure.configuration;

import br.com.msp.notificacoes.application.usecases.SendNotificationUseCase;
import br.com.msp.notificacoes.application.usecases.SendNotificationUseCaseImpl;
import br.com.msp.notificacoes.infrastructure.consumer.NotificationConsumer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    public NotificationConsumer agendamentoUseCase(SendNotificationUseCaseImpl sendNotificationUseCaseImpl) {
        return new NotificationConsumer(sendNotificationUseCaseImpl);
    }
}
