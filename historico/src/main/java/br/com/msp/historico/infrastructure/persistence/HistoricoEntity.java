package br.com.msp.historico.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_historico")
@Getter
@Setter
public class HistoricoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idConsultaAgendada;

    @Column(nullable = false)
    private Long pacienteId;

    @Column(nullable = false)
    private Long medicoId;
    private LocalDateTime dataRealizacao;
    private String especialidade;
    private String diagnostico;
    private String prescricao;
    private String observacoes;

}