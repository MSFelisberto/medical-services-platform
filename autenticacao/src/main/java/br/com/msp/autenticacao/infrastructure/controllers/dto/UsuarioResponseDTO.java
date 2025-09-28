package br.com.msp.autenticacao.infrastructure.controllers.dto;

public record UsuarioResponseDTO(
        Long id,
        String email,
        String perfil
) {}