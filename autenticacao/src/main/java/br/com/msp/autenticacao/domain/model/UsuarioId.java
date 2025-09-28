package br.com.msp.autenticacao.domain.model;

import java.util.Objects;

public class UsuarioId {
    private final Long value;

    public UsuarioId(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("ID do usuário deve ser um número positivo");
        }
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioId usuarioId = (UsuarioId) o;
        return Objects.equals(value, usuarioId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}