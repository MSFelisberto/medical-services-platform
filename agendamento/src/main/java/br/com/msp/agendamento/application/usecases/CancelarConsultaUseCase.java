package br.com.msp.agendamento.application.usecases;

import br.com.msp.agendamento.application.gateways.ConsultaGateway;
import br.com.msp.agendamento.domain.model.Consulta;
import br.com.msp.agendamento.infrastructure.producer.NotificationProducer;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CancelarConsultaUseCase {

    private final ConsultaGateway consultaGateway;
    private final NotificationProducer notificationProducer;

    public CancelarConsultaUseCase(ConsultaGateway consultaGateway, NotificationProducer notificationProducer) {
        this.consultaGateway = consultaGateway;
        this.notificationProducer = notificationProducer;
    }

    public void executar(Long consultaId) {
        Consulta consulta = consultaGateway.buscarPorId(consultaId)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada com o ID: " + consultaId));

        consulta.setCancelada(true);
        consultaGateway.cancelar(consulta);
        log.info("[CancelarConsultaUseCase] Enviando notificação de cancelamentto da consulta: {}",consulta.getId());
        notificationProducer.send(consulta);
    }
}
