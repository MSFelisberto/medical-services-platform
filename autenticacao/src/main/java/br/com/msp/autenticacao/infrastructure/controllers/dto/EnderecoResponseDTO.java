package br.com.msp.autenticacao.infrastructure.controllers.dto;

public record EnderecoResponseDTO(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {}