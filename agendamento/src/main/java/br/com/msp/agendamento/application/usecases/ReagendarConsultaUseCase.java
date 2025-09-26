package br.com.msp.agendamento.application.usecases;

import br.com.msp.agendamento.application.dto.ConsultaOutput;
import br.com.msp.agendamento.application.dto.ReagendarConsultaInput;
import br.com.msp.agendamento.application.gateways.ConsultaGateway;
import br.com.msp.agendamento.domain.model.Consulta;
import br.com.msp.agendamento.infrastructure.producer.NotificationProducer;
import br.com.msp.medicalcommons.enums.ETipoNotificacao;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReagendarConsultaUseCase {

    private final ConsultaGateway consultaGateway;
    private final NotificationProducer notificationProducer;

    public ReagendarConsultaUseCase(ConsultaGateway consultaGateway, NotificationProducer notificationProducer) {
        this.consultaGateway = consultaGateway;
        this.notificationProducer = notificationProducer;
    }

    public ConsultaOutput executar(ReagendarConsultaInput input) {
        Consulta consulta = consultaGateway.buscarPorId(input.consultaId())
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada com o ID: " + input.consultaId()));

        consulta.setPacienteId(input.pacienteId());
        consulta.setMedicoId(input.medicoId());
        consulta.setDataHora(input.dataHora());
        consulta.setEspecialidade(input.especialidade());

        Consulta consultaReagendada = consultaGateway.reagendar(consulta);

        log.info("[ReagendarConsultaUseCase] Enviando notificação de reagendamento: id {}", consulta.getId());
        notificationProducer.send(consulta, ETipoNotificacao.REAGENDAR);

        return new ConsultaOutput(
                consultaReagendada.getId(),
                consultaReagendada.getPacienteId(),
                consultaReagendada.getMedicoId(),
                consultaReagendada.getDataHora(),
                consultaReagendada.getEspecialidade()
        );
    }
}
