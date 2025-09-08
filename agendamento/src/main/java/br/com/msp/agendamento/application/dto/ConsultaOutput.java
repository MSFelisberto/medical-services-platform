package br.com.msp.agendamento.application.dto;

import java.time.LocalDateTime;

public record ConsultaOutput(
        Long id,
        Long pacienteId,
        Long medicoId,
        LocalDateTime dataHora,
        String especialidade
) {
}
