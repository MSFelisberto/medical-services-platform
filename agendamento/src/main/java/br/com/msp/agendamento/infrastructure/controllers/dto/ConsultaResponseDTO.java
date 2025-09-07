package br.com.msp.agendamento.infrastructure.controllers.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(
        Long id,
        Long pacienteId,
        Long medicoId,
        LocalDateTime dataHora,
        String especialidade
) {
}
