package br.com.msp.historico.application.dto;

import java.time.LocalDateTime;

public record HistoricoDTO(
    Long id,
    LocalDateTime dataRealizacao,
    String especialidade,
    String diagnostico,
    String prescricao,
    String observacoes
) {}