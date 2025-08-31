package br.com.msp.autenticacao.application.models;

import br.com.msp.autenticacao.domain.Perfil;

public record CadastrarUsuarioInput(
        String email,
        String senha,
        Perfil perfil
) {
}
