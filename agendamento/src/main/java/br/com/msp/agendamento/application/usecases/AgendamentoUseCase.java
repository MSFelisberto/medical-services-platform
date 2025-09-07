package br.com.msp.agendamento.application.usecases;

import br.com.msp.agendamento.application.dto.AgendarConsultaInput;
import br.com.msp.agendamento.application.dto.ConsultaOutput;
import br.com.msp.agendamento.application.dto.ReagendarConsultaInput;

import java.util.List;

public interface AgendamentoUseCase {
    ConsultaOutput agendarConsulta(AgendarConsultaInput input);
    ConsultaOutput reagendarConsulta(ReagendarConsultaInput input);
    void cancelarConsulta(Long consultaId);
    List<ConsultaOutput> listarConsultasPorPaciente(Long pacienteId);
}
