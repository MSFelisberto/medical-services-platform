package br.com.msp.autenticacao.application.dto;

import java.time.LocalDate;

public record CadastrarPacienteCommand(
        String email,
        String senha,
        String nomeCompleto,
        String cpf,
        LocalDate dataNascimento,
        String telefone,
        EnderecoDTO endereco
) {}