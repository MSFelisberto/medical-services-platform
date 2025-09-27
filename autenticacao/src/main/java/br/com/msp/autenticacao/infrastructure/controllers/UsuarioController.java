package br.com.msp.autenticacao.infrastructure.controllers;

import br.com.msp.autenticacao.application.usecases.CadastrarUsuarioUseCase;
import br.com.msp.autenticacao.application.usecases.ValidarPacienteUseCase;
import br.com.msp.autenticacao.infrastructure.controllers.dto.UsuarioRequestDTO;
import br.com.msp.autenticacao.infrastructure.controllers.mappers.UsuarioMapper;
import br.com.msp.autenticacao.infrastructure.persistence.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(CadastrarUsuarioUseCase cadastrarUsuarioUseCase, UsuarioMapper usuarioMapper) {
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
        this.usuarioMapper = usuarioMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> cadastrar(@RequestBody @Valid UsuarioRequestDTO resquestDTO) {

        var input = usuarioMapper.toInput(resquestDTO);

        cadastrarUsuarioUseCase.cadastrarUsuario(input);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }
}

