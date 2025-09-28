package br.com.msp.autenticacao.application.dto;

import br.com.msp.autenticacao.domain.funcionario.model.TipoFuncionario;
import java.time.LocalDateTime;

public record FuncionarioOutput(
        Long id,
        String email,
        TipoFuncionario tipo,
        String nomeCompleto,
        String cpf,
        String crm,
        String coren,
        EspecialidadeDTO especialidade,
        boolean ativo,
        LocalDateTime dataCadastro
) {}