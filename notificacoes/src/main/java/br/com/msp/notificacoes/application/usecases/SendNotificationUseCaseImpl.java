package br.com.msp.notificacoes.application.usecases;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Slf4j
@Component
public class SendNotificationUseCaseImpl implements SendNotificationUseCase {

    @Override
    public void sendNotification(ConsultaInput consulta, String topico) {
        log.info("[SendNotificationUseCaseImpl] Enviando e-mail de " + topico);
    }
}
