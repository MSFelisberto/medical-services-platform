package br.com.msp.autenticacao.domain.model;

import br.com.msp.autenticacao.domain.exception.UsuarioBusinessException;
import java.util.Objects;

public class Senha {
    private static final int MIN_LENGTH = 6;
    private final String value;

    public Senha(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new UsuarioBusinessException("Senha não pode ser vazia");
        }

        if (value.length() < MIN_LENGTH) {
            throw new UsuarioBusinessException(
                    "Senha deve ter no mínimo " + MIN_LENGTH + " caracteres"
            );
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Senha senha = (Senha) o;
        return Objects.equals(value, senha.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}