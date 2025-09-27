package br.com.msp.agendamento.application.ports.outbound;

import br.com.msp.agendamento.domain.model.Consulta;

public interface NotificationService {
    void notificarAgendamento(Consulta consulta);
    void notificarCancelamento(Consulta consulta);
    void notificarReagendamento(Consulta consulta);
}