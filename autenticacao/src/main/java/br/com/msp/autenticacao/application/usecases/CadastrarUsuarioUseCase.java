package br.com.msp.autenticacao.application.usecases;

import br.com.msp.autenticacao.application.gateways.UsuarioGateway;
import br.com.msp.autenticacao.application.models.CadastrarUsuarioInput;
import br.com.msp.autenticacao.domain.Usuario;

public class CadastrarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public CadastrarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Usuario cadastrarUsuario(CadastrarUsuarioInput input) {
        if (usuarioGateway.findByEmail(input.email()).isPresent()) {
            throw new IllegalArgumentException("Usuário com este e-mail já existe.");
        }

        Usuario novoUsuario = new Usuario(input.email(), input.senha(), input.perfil());

        return usuarioGateway.cadastrar(novoUsuario);
    }
}
