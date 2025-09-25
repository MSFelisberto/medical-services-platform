package br.com.msp.agendamento.application.usecases;

import br.com.msp.agendamento.application.dto.AgendarConsultaInput;
import br.com.msp.agendamento.application.dto.ConsultaOutput;
import br.com.msp.agendamento.application.gateways.ConsultaGateway;
import br.com.msp.agendamento.domain.model.Consulta;
import br.com.msp.agendamento.infrastructure.controllers.dto.ConsultaResponseDTO;
import br.com.msp.agendamento.infrastructure.controllers.mappers.ConsultaDTOMapper;
import br.com.msp.agendamento.infrastructure.producer.NotificationProducer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgendarConsultaUseCase {

    private final ConsultaGateway consultaGateway;

    private final NotificationProducer notificationProducer;

    public AgendarConsultaUseCase(ConsultaGateway consultaGateway, NotificationProducer notificationProducer) {
        this.consultaGateway = consultaGateway;
        this.notificationProducer = notificationProducer;
    }

    public ConsultaOutput executar(AgendarConsultaInput input) {
        Consulta novaConsulta = new Consulta();
        novaConsulta.setPacienteId(input.pacienteId());
        novaConsulta.setMedicoId(input.medicoId());
        novaConsulta.setDataHora(input.dataHora());
        novaConsulta.setEspecialidade(input.especialidade());
        novaConsulta.setCancelada(false);

        Consulta consultaAgendada = consultaGateway.agendar(novaConsulta);
        log.info("[AgendarConsultaUseCase] Enviando notificação de agendamento de consulta: id {}", consultaAgendada.getId());
        notificationProducer.send(consultaAgendada);

        return new ConsultaOutput(
                consultaAgendada.getId(),
                consultaAgendada.getPacienteId(),
                consultaAgendada.getMedicoId(),
                consultaAgendada.getDataHora(),
                consultaAgendada.getEspecialidade()
        );
    }
}
