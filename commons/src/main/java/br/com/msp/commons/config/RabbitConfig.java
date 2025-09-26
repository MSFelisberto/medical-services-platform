package br.com.msp.commons.config;

public final class RabbitConfig {

    private RabbitConfig() {
        throw new UnsupportedOperationException("Classe utilitária não pode ser instanciada");
    }

    public static final String EXCHANGE_NAME = "notificacoes";

    public static final String QUEUE_AGENDAR = "notificacao.agendar.queue";
    public static final String QUEUE_CANCELAR = "notificacao.cancelar.queue";
    public static final String QUEUE_REAGENDAR = "notificacao.reagendar.queue";
    public static final String QUEUE_HISTORICO = "notificacao.historico.queue";

    public static final String ROUTING_KEY_HISTORICO = "notificacao.historico";
    public static final String ROUTING_KEY_AGENDAR = "notificacao.agendar";
    public static final String ROUTING_KEY_CANCELAR = "notificacao.cancelar";
    public static final String ROUTING_KEY_REAGENDAR = "notificacao.reagendar";
}
