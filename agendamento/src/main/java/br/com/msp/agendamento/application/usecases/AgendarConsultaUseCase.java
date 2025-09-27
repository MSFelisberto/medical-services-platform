package br.com.msp.agendamento.application.usecases;

import br.com.msp.agendamento.application.dto.AgendarConsultaInput;
import br.com.msp.agendamento.application.dto.ConsultaOutput;
import br.com.msp.agendamento.application.gateways.ConsultaGateway;
import br.com.msp.agendamento.application.gateways.PacienteGateway;
import br.com.msp.agendamento.domain.exception.PacienteNotFoundException;
import br.com.msp.agendamento.domain.model.Consulta;
import br.com.msp.agendamento.infrastructure.producer.NotificationProducer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgendarConsultaUseCase {

    private final ConsultaGateway consultaGateway;
    private final PacienteGateway pacienteGateway;
    private final NotificationProducer notificationProducer;

    public AgendarConsultaUseCase(ConsultaGateway consultaGateway,
                                  PacienteGateway pacienteGateway,
                                  NotificationProducer notificationProducer) {
        this.consultaGateway = consultaGateway;
        this.pacienteGateway = pacienteGateway;
        this.notificationProducer = notificationProducer;
    }

    public ConsultaOutput executar(AgendarConsultaInput input) {
        if (!pacienteGateway.existePaciente(input.pacienteId())) {
            throw new PacienteNotFoundException("Paciente não encontrado com ID: " + input.pacienteId());
        }

        Consulta novaConsulta = new Consulta();
        novaConsulta.setPacienteId(input.pacienteId());
        novaConsulta.setMedicoId(input.medicoId());
        novaConsulta.setDataHora(input.dataHora());
        novaConsulta.setEspecialidade(input.especialidade());
        novaConsulta.setCancelada(false);

        Consulta consultaAgendada = consultaGateway.agendar(novaConsulta);
        log.info("[AgendarConsultaUseCase] Enviando notificação de agendamento de consulta: id {}", consultaAgendada.getId());
        notificationProducer.sendAgendar(consultaAgendada);

        return new ConsultaOutput(
                consultaAgendada.getId(),
                consultaAgendada.getPacienteId(),
                consultaAgendada.getMedicoId(),
                consultaAgendada.getDataHora(),
                consultaAgendada.getEspecialidade()
        );
    }
}
