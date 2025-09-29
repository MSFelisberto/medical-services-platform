package br.com.msp.historico.application.dto;

import java.time.LocalDateTime;

public record CriarHistoricoCommand(
    Long pacienteId,
    Long medicoId,
    LocalDateTime dataRealizacao,
    String especialidade
) {}