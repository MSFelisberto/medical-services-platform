package br.com.msp.autenticacao.infrastructure.persistence;

import br.com.msp.autenticacao.application.gateways.UsuarioGateway;
import br.com.msp.autenticacao.domain.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioGatewayImpl(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity(
                null,
                usuario.getEmail(),
                passwordEncoder.encode(usuario.getSenha()),
                usuario.getPerfil()
        );
        UsuarioEntity savedEntity = repository.save(entity);
        usuario.setId(savedEntity.getId());
        return usuario;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(entity -> new Usuario(entity.getEmail(), entity.getSenha(), entity.getPerfil()));
    }
}
