package br.com.msp.autenticacao.infrastructure.controllers.dto;

import br.com.msp.autenticacao.application.dto.FuncionarioOutput;
import br.com.msp.autenticacao.domain.funcionario.model.TipoFuncionario;
import java.time.LocalDateTime;

public record FuncionarioResponseDTO(
        Long id,
        String email,
        TipoFuncionario tipo,
        String nomeCompleto,
        String cpf,
        String crm,
        String coren,
        EspecialidadeResponseDTO especialidade,
        boolean ativo,
        LocalDateTime dataCadastro
) {
    public static FuncionarioResponseDTO fromOutput(FuncionarioOutput output) {
        EspecialidadeResponseDTO especialidadeDTO = null;
        if (output.especialidade() != null) {
            especialidadeDTO = new EspecialidadeResponseDTO(
                    output.especialidade().nome(),
                    output.especialidade().codigo()
            );
        }

        return new FuncionarioResponseDTO(
                output.id(),
                output.email(),
                output.tipo(),
                output.nomeCompleto(),
                output.cpf(),
                output.crm(),
                output.coren(),
                especialidadeDTO,
                output.ativo(),
                output.dataCadastro()
        );
    }
}