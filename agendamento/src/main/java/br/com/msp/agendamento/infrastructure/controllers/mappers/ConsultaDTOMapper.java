package br.com.msp.agendamento.infrastructure.controllers.mappers;

import br.com.msp.agendamento.application.dto.AgendarConsultaInput;
import br.com.msp.agendamento.application.dto.ConsultaOutput;
import br.com.msp.agendamento.application.dto.ReagendarConsultaInput;
import br.com.msp.agendamento.domain.model.Consulta;
import br.com.msp.agendamento.infrastructure.controllers.dto.ConsultaRequestDTO;
import br.com.msp.agendamento.infrastructure.controllers.dto.ConsultaResponseDTO;
import br.com.msp.medicalcommons.dtos.ConsultaDTO;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDTOMapper {

    public AgendarConsultaInput toInput(ConsultaRequestDTO requestDTO) {
        return new AgendarConsultaInput(
                requestDTO.pacienteId(),
                requestDTO.medicoId(),
                requestDTO.dataHora(),
                requestDTO.especialidade()
        );
    }

    public ReagendarConsultaInput toReagendarInput(Long consultaId, ConsultaRequestDTO requestDTO) {
        return new ReagendarConsultaInput(
                consultaId,
                requestDTO.pacienteId(),
                requestDTO.medicoId(),
                requestDTO.dataHora(),
                requestDTO.especialidade()
        );
    }

    public ConsultaResponseDTO toResponse(ConsultaOutput output) {
        return new ConsultaResponseDTO(
                output.id(),
                output.pacienteId(),
                output.medicoId(),
                output.dataHora(),
                output.especialidade()
        );
    }

    public static ConsultaDTO toDTO(Consulta consulta) {
        return new ConsultaDTO(
                consulta.getPacienteId(),
                consulta.getMedicoId(),
                consulta.getDataHora(),
                consulta.getEspecialidade()
        );
    }

}
