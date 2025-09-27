package br.com.msp.agendamento.infrastructure.controllers.dto;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(
        Long id,
        Long pacienteId,
        Long medicoId,
        LocalDateTime dataHora,
        String especialidade,
        String status
) {
}