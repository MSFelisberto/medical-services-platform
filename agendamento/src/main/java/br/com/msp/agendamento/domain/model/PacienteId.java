package br.com.msp.agendamento.domain.model;

import java.util.Objects;

public class PacienteId {
    private final Long value;

    public PacienteId(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("ID do paciente deve ser um número positivo");
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
        PacienteId that = (PacienteId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}