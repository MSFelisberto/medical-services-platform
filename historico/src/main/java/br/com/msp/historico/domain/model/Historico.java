package br.com.msp.historico.domain.model;

import java.time.LocalDateTime;

public class Historico {
    private Long id;
    private Long idConsultaAgendada;
    private Long pacienteId;
    private Long medicoId;
    private LocalDateTime dataRealizacao;
    private String especialidade;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdConsultaAgendada() {
        return idConsultaAgendada;
    }

    public void setIdConsultaAgendada(Long idConsultaAgendada) {
        this.idConsultaAgendada = idConsultaAgendada;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDateTime getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDateTime dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
