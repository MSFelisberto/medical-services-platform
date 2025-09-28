package br.com.msp.autenticacao.infrastructure.controllers.dto;

public record AuthResponseDTO(
        String token,
        String type,
        Long expiresIn
) {}