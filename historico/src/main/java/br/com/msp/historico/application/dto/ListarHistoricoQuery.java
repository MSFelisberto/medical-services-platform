package br.com.msp.historico.application.dto;

public record ListarHistoricoQuery(
        Long pacienteId,
        AuthenticatedUser currentUser
) {}