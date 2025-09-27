CREATE TABLE tb_historico (
    id BIGSERIAL PRIMARY KEY,
    id_consulta_agendada BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    data_realizacao TIMESTAMP,
    especialidade VARCHAR(255),
    diagnostico VARCHAR(255),
    prescricao VARCHAR(255),
    observacoes VARCHAR(255)
);
