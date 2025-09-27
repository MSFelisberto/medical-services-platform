package br.com.msp.agendamento.application.ports.outbound;

import br.com.msp.agendamento.domain.model.PacienteId;

public interface PacienteService {
    boolean exists(PacienteId pacienteId);
}