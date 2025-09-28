package br.com.msp.autenticacao.domain.model;

import br.com.msp.autenticacao.domain.exception.UsuarioBusinessException;
import java.util.Objects;

public class Usuario {
    private UsuarioId id;
    private final Email email;
    private final Senha senha;
    private final Perfil perfil;

    // Construtor para criação (sem ID)
    public Usuario(Email email, Senha senha, Perfil perfil) {
        validarDadosObrigatorios(email, senha, perfil);
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    // Construtor para reconstrução (com ID)
    public Usuario(UsuarioId id, Email email, Senha senha, Perfil perfil) {
        validarDadosObrigatorios(email, senha, perfil);
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    public boolean isPaciente() {
        return this.perfil == Perfil.PACIENTE;
    }

    public boolean hasRole(String role) {
        return this.perfil.name().equals(role.toUpperCase());
    }

    private void validarDadosObrigatorios(Email email, Senha senha, Perfil perfil) {
        if (email == null) {
            throw new UsuarioBusinessException("E-mail é obrigatório");
        }
        if (senha == null) {
            throw new UsuarioBusinessException("Senha é obrigatória");
        }
        if (perfil == null) {
            throw new UsuarioBusinessException("Perfil é obrigatório");
        }
    }

    // Getters
    public UsuarioId getId() { return id; }
    public Email getEmail() { return email; }
    public Senha getSenha() { return senha; }
    public Perfil getPerfil() { return perfil; }

    // Setter apenas para ID (usado na persistência)
    public void setId(UsuarioId id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}