package br.com.msp.autenticacao.infrastructure.controllers.mappers;

import br.com.msp.autenticacao.application.inputs.CadastrarUsuarioInput;
import br.com.msp.autenticacao.infrastructure.controllers.dto.UsuarioRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public CadastrarUsuarioInput toInput(UsuarioRequestDTO resquestDTO) {
        return new CadastrarUsuarioInput(
                resquestDTO.email(),
                resquestDTO.senha(),
                resquestDTO.perfil()
        );
    }
}
