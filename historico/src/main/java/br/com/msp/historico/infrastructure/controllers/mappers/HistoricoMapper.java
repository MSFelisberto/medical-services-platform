package br.com.msp.historico.infrastructure.controllers.mappers;

import br.com.msp.historico.domain.model.Historico;
import br.com.msp.historico.infrastructure.persistence.HistoricoEntity;

public class HistoricoMapper {

    public static HistoricoEntity toModel(Historico domain) {
        HistoricoEntity model = new HistoricoEntity();
        model.setId(domain.getId());
        model.setDataRealizacao(domain.getDataRealizacao());
        model.setEspecialidade(domain.getEspecialidade());
        model.setMedicoId(domain.getMedicoId());
        model.setPacienteId(domain.getPacienteId());
        return model;
    }

    public static Historico toDomain(HistoricoEntity model) {
        Historico domain = new Historico();
        domain.setId(model.getId());
        domain.setDataRealizacao(model.getDataRealizacao());
        domain.setEspecialidade(model.getEspecialidade());
        domain.setMedicoId(model.getMedicoId());
        domain.setPacienteId(model.getPacienteId());
        return domain;
    }
}