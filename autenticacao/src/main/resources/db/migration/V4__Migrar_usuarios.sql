
-- Migrar pacientes da tabela antiga para a nova
INSERT INTO tb_pacientes (email, senha, nome_completo, cpf, data_nascimento, telefone,
                          logradouro, numero, bairro, cidade, estado, cep)
SELECT
    email,
    senha,
    'Paciente Teste', -- Placeholder - seria necessário ter essa informação
    '00000000000', -- Placeholder - seria necessário ter essa informação
    '1990-01-01', -- Placeholder
    '(11) 99999-9999', -- Placeholder
    'Rua Exemplo, 123', -- Placeholder
    '123', -- Placeholder
    'Centro', -- Placeholder
    'São Paulo', -- Placeholder
    'SP', -- Placeholder
    '01000000' -- Placeholder
FROM tb_usuarios
WHERE perfil = 'PACIENTE';


-- Migrar funcionários da tabela antiga para a nova
INSERT INTO tb_funcionarios (email, senha, tipo, nome_completo, cpf, crm, coren,
                             especialidade_nome, especialidade_codigo)
SELECT
    email,
    senha,
    perfil::VARCHAR, -- Cast enum para string
    'Nome do Funcionário', -- Placeholder
    '00000000000', -- Placeholder
    CASE WHEN perfil = 'MEDICO' THEN 'CRM123456' ELSE NULL END,
    CASE WHEN perfil = 'ENFERMEIRO' THEN 'COREN123456' ELSE NULL END,
    CASE WHEN perfil = 'MEDICO' THEN 'CLINICA GERAL' ELSE NULL END,
    CASE WHEN perfil = 'MEDICO' THEN 'CG001' ELSE NULL END
FROM tb_usuarios
WHERE perfil IN ('ADMIN', 'MEDICO', 'ENFERMEIRO');


