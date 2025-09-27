// Pacote: br.com.msp.historico.application.dto
package br.com.msp.historico.application.dto;

import java.time.LocalDateTime;

// Um "Command" para criar um novo histórico. Imutável e explícito.
public record CriarHistoricoCommand(
    Long idConsultaAgendada,
    Long pacienteId,
    Long medicoId,
    LocalDateTime dataRealizacao,
    String especialidade,
    String diagnostico,
    String prescricao,
    String observacoes
) {}