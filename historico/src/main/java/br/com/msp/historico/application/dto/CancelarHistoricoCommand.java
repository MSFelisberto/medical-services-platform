package br.com.msp.historico.application.dto;

public record CancelarHistoricoCommand(
        Long consultaId,
        String motivoCancelamento
) {}