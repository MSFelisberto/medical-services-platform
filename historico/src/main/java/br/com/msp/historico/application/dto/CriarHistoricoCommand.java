package br.com.msp.historico.application.dto;

import java.time.LocalDateTime;

public record CriarHistoricoCommand(
    Long idConsultaAgendada,
    Long pacienteId,
    Long medicoId,
    LocalDateTime dataRealizacao,
    String especialidade,
    String status
) {}