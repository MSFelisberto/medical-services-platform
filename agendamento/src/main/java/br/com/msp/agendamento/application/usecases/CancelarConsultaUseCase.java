package br.com.msp.agendamento.application.usecases;

import br.com.msp.agendamento.application.gateways.ConsultaGateway;
import br.com.msp.agendamento.domain.model.Consulta;
import jakarta.persistence.EntityNotFoundException;

public class CancelarConsultaUseCase {

    private final ConsultaGateway consultaGateway;

    public CancelarConsultaUseCase(ConsultaGateway consultaGateway) {
        this.consultaGateway = consultaGateway;
    }

    public void executar(Long consultaId) {
        Consulta consulta = consultaGateway.buscarPorId(consultaId)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada com o ID: " + consultaId));

        consulta.setCancelada(true);
        consultaGateway.cancelar(consulta);

        //TODO: Enviar evento de cancelamento para a fila de Notificações
    }
}
