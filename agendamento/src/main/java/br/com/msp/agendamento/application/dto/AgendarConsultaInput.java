package br.com.msp.agendamento.application.dto;

import java.time.LocalDateTime;

public record AgendarConsultaInput(
        Long pacienteId,
        Long medicoId,
        LocalDateTime dataHora,
        String especialidade
) {
}
