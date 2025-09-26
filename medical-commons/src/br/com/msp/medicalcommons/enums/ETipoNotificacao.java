package br.com.msp.medicalcommons.enums;

public enum ETipoNotificacao {

    AGENDAR("notificacao.agendar"),
    CANCELAR("notificacao.cancelar"),
    REAGENDAR("notificacao.reagendar");

    private final String routingKey;

    ETipoNotificacao(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getRoutingKey() {
        return routingKey;
    }
}
