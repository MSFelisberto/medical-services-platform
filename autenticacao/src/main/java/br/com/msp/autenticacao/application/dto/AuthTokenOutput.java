package br.com.msp.autenticacao.application.dto;

public record AuthTokenOutput(
        String token,
        String type,
        Long expiresIn,
        String userType
) {}