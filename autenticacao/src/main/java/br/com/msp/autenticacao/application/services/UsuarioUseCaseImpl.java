package br.com.msp.autenticacao.application.services;

import br.com.msp.autenticacao.application.dto.CadastrarUsuarioCommand;
import br.com.msp.autenticacao.application.dto.UsuarioOutput;
import br.com.msp.autenticacao.application.dto.ValidarPacienteQuery;
import br.com.msp.autenticacao.application.ports.inbound.UsuarioUseCase;
import br.com.msp.autenticacao.application.ports.outbound.PasswordEncoder;
import br.com.msp.autenticacao.application.ports.outbound.UsuarioRepository;
import br.com.msp.autenticacao.domain.exception.EmailJaExisteException;
import br.com.msp.autenticacao.domain.model.*;

public class UsuarioUseCaseImpl implements UsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioUseCaseImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioOutput cadastrarUsuario(CadastrarUsuarioCommand command) {
        Email email = new Email(command.email());

        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new EmailJaExisteException("Usuário com este e-mail já existe: " + email.getValue());
        }

        Senha senha = new Senha(command.senha());
        Usuario usuario = new Usuario(email, senha, command.perfil());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return mapToOutput(usuarioSalvo);
    }

    @Override
    public boolean validarPacienteExiste(ValidarPacienteQuery query) {
        try {
            UsuarioId usuarioId = new UsuarioId(query.pacienteId());
            return usuarioRepository.findById(usuarioId)
                    .map(Usuario::isPaciente)
                    .orElse(false);
        } catch (Exception e) {
            return false;
        }
    }

    private UsuarioOutput mapToOutput(Usuario usuario) {
        return new UsuarioOutput(
                usuario.getId().getValue(),
                usuario.getEmail().getValue(),
                usuario.getPerfil()
        );
    }
}