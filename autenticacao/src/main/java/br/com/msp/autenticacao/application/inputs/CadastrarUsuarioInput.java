package br.com.msp.autenticacao.application.inputs;

import br.com.msp.autenticacao.domain.Perfil;

public record CadastrarUsuarioInput(
        String email,
        String senha,
        Perfil perfil
) {
}
