package br.com.msp.agendamento.application.dto;

import java.time.LocalDateTime;

public record ReagendarConsultaCommand(
        Long consultaId,
        Long medicoId,
        LocalDateTime dataHora,
        String especialidade
) {}