package br.com.msp.autenticacao.application.dto;

import br.com.msp.autenticacao.domain.funcionario.model.TipoFuncionario;

public record CadastrarFuncionarioCommand(
        String email,
        String senha,
        TipoFuncionario tipo,
        String nomeCompleto,
        String cpf,
        String crm,
        String coren,
        EspecialidadeDTO especialidade
) {}