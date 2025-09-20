package br.com.msp.historico.infrastructure.controllers.mappers;

import br.com.msp.historico.domain.model.Historico;
import br.com.msp.historico.infrastructure.persistence.HistoricoEntity;

public class HistoricoMapper {

    public static HistoricoEntity toModel(Historico domain) {
        HistoricoEntity model = new HistoricoEntity();
        model.setId(domain.getId());
        model.setDiagnostico(domain.getDiagnostico());
        model.setDataRealizacao(domain.getDataRealizacao());
        model.setEspecialidade(domain.getEspecialidade());
        model.setObservacoes(domain.getObservacoes());
        model.setMedicoId(domain.getMedicoId());
        model.setPrescricao(domain.getPrescricao());
        model.setPacienteId(domain.getPacienteId());
        model.setIdConsultaAgendada(domain.getIdConsultaAgendada());
        return model;
    }

    public static Historico toDomain(HistoricoEntity model) {
        Historico domain = new Historico();
        domain.setId(model.getId());
        domain.setDiagnostico(model.getDiagnostico());
        domain.setDataRealizacao(model.getDataRealizacao());
        domain.setEspecialidade(model.getEspecialidade());
        domain.setObservacoes(model.getObservacoes());
        domain.setMedicoId(model.getMedicoId());
        domain.setPrescricao(model.getPrescricao());
        domain.setPacienteId(model.getPacienteId());
        domain.setIdConsultaAgendada(model.getIdConsultaAgendada());
        return domain;
    }
}