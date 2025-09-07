package br.com.msp.agendamento.application.dto;

import java.time.LocalDateTime;

public record ReagendarConsultaInput(
        Long consultaId,
        Long pacienteId,
        Long medicoId,
        LocalDateTime dataHora,
        String especialidade
) {
}
