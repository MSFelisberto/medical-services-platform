package br.com.msp.historico.application.dto;

import java.time.LocalDateTime;

public record RegistrarHistoricoCommand(
        Long consultaId,
        Long pacienteId,
        Long medicoId,
        LocalDateTime dataHora,
        String especialidade
) {}