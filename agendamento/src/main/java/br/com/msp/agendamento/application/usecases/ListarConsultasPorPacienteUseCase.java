package br.com.msp.agendamento.application.usecases;

import br.com.msp.agendamento.application.dto.AuthenticatedUser;
import br.com.msp.agendamento.application.dto.ConsultaOutput;
import br.com.msp.agendamento.application.gateways.ConsultaGateway;
import br.com.msp.agendamento.domain.exception.AuthorizationException;
import br.com.msp.agendamento.infrastructure.producer.NotificationProducer;

import java.util.List;
import java.util.stream.Collectors;

public class ListarConsultasPorPacienteUseCase {

    private final ConsultaGateway consultaGateway;

    private final NotificationProducer notificationProducer;

    public ListarConsultasPorPacienteUseCase(ConsultaGateway consultaGateway, NotificationProducer notificationProducer) {
        this.consultaGateway = consultaGateway;
        this.notificationProducer = notificationProducer;
    }

    public List<ConsultaOutput> executar(Long pacienteId, AuthenticatedUser currentUser) {

        if (currentUser.hasRole("PACIENTE")) {
            if (!pacienteId.equals(currentUser.getId())) {
                throw new AuthorizationException("Acesso negado. Paciente só pode visualizar as próprias consultas.");
            }
        }

        return consultaGateway.buscarPorPacienteId(pacienteId).stream()
                .map(consulta -> new ConsultaOutput(
                        consulta.getId(),
                        consulta.getPacienteId(),
                        consulta.getMedicoId(),
                        consulta.getDataHora(),
                        consulta.getEspecialidade()
                ))
                .collect(Collectors.toList());
    }
}
