package br.com.msp.agendamento.application.dto;

public record ListarConsultasQuery(
        Long pacienteId,
        AuthenticatedUser currentUser
) {}