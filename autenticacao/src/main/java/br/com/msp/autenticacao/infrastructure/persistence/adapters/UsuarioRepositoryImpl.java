package br.com.msp.autenticacao.infrastructure.persistence.adapters;

import br.com.msp.autenticacao.application.ports.outbound.UsuarioRepository;
import br.com.msp.autenticacao.domain.model.*;
import br.com.msp.autenticacao.infrastructure.persistence.entity.UsuarioEntity;
import br.com.msp.autenticacao.infrastructure.persistence.repository.UsuarioJPARepository;
import br.com.msp.autenticacao.application.ports.outbound.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJPARepository jpaRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioRepositoryImpl(UsuarioJPARepository jpaRepository, PasswordEncoder passwordEncoder) {
        this.jpaRepository = jpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = toEntity(usuario);
        UsuarioEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Usuario> findByEmail(Email email) {
        return jpaRepository.findByEmail(email.getValue())
                .map(this::toDomain);
    }

    @Override
    public Optional<Usuario> findById(UsuarioId id) {
        return jpaRepository.findById(id.getValue())
                .map(this::toDomain);
    }

    private UsuarioEntity toEntity(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();

        if (usuario.getId() != null) {
            entity.setId(usuario.getId().getValue());
        }

        entity.setEmail(usuario.getEmail().getValue());

        // Se a senha já está encodada (recuperação do banco), não encode novamente
        String senhaValue = usuario.getSenha().getValue();
        if (isPasswordEncoded(senhaValue)) {
            entity.setSenha(senhaValue);
        } else {
            entity.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        entity.setPerfil(usuario.getPerfil());
        return entity;
    }

    private Usuario toDomain(UsuarioEntity entity) {
        Email email = new Email(entity.getEmail());

        // Para senhas já encodadas vindas do banco, criamos um wrapper especial
        Senha senha = createSenhaFromEncoded(entity.getSenha());

        if (entity.getId() != null) {
            UsuarioId id = new UsuarioId(entity.getId());
            return new Usuario(id, email, senha, entity.getPerfil());
        }

        return new Usuario(email, senha, entity.getPerfil());
    }

    /**
     * Verifica se a senha já está encodada (hash BCrypt)
     */
    private boolean isPasswordEncoded(String password) {
        return password != null && password.startsWith("$2a$") && password.length() == 60;
    }

    /**
     * Cria uma instância de Senha para senhas já encodadas vindas do banco
     * Usamos uma senha temporária válida para criar o Value Object,
     * mas mantemos o valor encoded original
     */
    private Senha createSenhaFromEncoded(String encodedPassword) {
        return new SenhaEncoded(encodedPassword);
    }

    /**
     * Classe interna para representar senhas já encodadas
     */
    private static class SenhaEncoded extends Senha {
        private final String encodedValue;

        public SenhaEncoded(String encodedValue) {
            super("temp123"); // Senha temporária válida apenas para criar o objeto
            this.encodedValue = encodedValue;
        }

        @Override
        public String getValue() {
            return encodedValue;
        }
    }
}