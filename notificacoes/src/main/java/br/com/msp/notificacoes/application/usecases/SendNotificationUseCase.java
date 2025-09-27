package br.com.msp.notificacoes.application.usecases;

public interface SendNotificationUseCase {
    void sendNotification(ConsultaInput consulta, String topico);
}
