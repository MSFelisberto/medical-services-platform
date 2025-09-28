package br.com.msp.autenticacao.application.dto;

import br.com.msp.autenticacao.domain.model.Perfil;

public record UsuarioOutput(
        Long id,
        String email,
        Perfil perfil
) {}