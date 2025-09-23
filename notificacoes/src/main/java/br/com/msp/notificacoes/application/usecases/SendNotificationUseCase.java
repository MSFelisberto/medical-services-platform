package br.com.msp.notificacoes.application.usecases;

public interface SendNotificationUseCase<T, R> {
    R sendNotification(T data);
}
