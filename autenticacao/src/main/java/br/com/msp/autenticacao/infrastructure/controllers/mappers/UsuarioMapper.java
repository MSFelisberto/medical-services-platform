package br.com.msp.autenticacao.infrastructure.controllers.mappers;

import br.com.msp.autenticacao.application.models.CadastrarUsuarioInput;
import br.com.msp.autenticacao.infrastructure.controllers.dto.UsuarioResquestDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public CadastrarUsuarioInput toInput(UsuarioResquestDTO resquestDTO) {
        return new CadastrarUsuarioInput(
                resquestDTO.email(),
                resquestDTO.senha(),
                resquestDTO.perfil()
        );
    }
}
