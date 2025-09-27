package br.com.msp.agendamento.application.ports.inbound;

import br.com.msp.agendamento.application.dto.*;
import java.util.List;

public interface AgendamentoUseCase {
    ConsultaOutput agendarConsulta(AgendarConsultaCommand command);
    ConsultaOutput reagendarConsulta(ReagendarConsultaCommand command);
    void cancelarConsulta(CancelarConsultaCommand command);
    List<ConsultaOutput> listarConsultasPorPaciente(ListarConsultasQuery query);
}