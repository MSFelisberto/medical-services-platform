package br.com.msp.historico.application.dto;

import java.time.LocalDateTime;

public record AtualizarHistoricoCommand(
        Long consultaId,
        LocalDateTime novaDataHora,
        Long novoMedicoId,
        String novaEspecialidade
) {}