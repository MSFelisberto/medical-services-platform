package br.com.msp.autenticacao.application.dto;

import br.com.msp.autenticacao.domain.model.Perfil;

public record CadastrarUsuarioCommand(
        String email,
        String senha,
        Perfil perfil
) {}