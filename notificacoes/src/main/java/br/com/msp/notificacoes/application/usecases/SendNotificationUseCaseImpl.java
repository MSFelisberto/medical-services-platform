package br.com.msp.notificacoes.application.usecases;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class SendNotificationUseCaseImpl implements SendNotificationUseCase {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public void sendNotification(ConsultaInput consulta) {
        this.log.info("Enviando e-mail de agendamento");
    }
}
