package br.com.msp.autenticacao.application.dto;

public record AutenticarCommand(
        String email,
        String senha
) {}
