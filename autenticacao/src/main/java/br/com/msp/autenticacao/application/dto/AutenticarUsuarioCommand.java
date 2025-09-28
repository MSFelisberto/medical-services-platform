package br.com.msp.autenticacao.application.dto;

public record AutenticarUsuarioCommand(
        String email,
        String senha
) {}