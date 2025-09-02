package br.com.msp.autenticacao.infrastructure.controllers.dto;

import br.com.msp.autenticacao.domain.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        @NotBlank(message = "O e-mail não pode ser vazio.")
        @Email(message = "Formato de e-mail inválido.")
        String email,

        @NotBlank(message = "A senha não pode ser vazia.")
        @Size(min = 6, message = "A senha deve terno mínimo 6 caracteres.")
        String senha,

        @NotNull(message = "O perfil não pode ser nulo.")
        Perfil perfil
) {
}
