package br.com.msp.notificacoes.infrastructure.controllers;

import br.com.msp.notificacoes.infrastructure.dtos.ConsultaDTO;
import br.com.msp.notificacoes.infrastructure.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<Void> criarConsulta(@RequestBody @Valid ConsultaDTO consultaDTO) {
        consultaService.agendarConsulta(consultaDTO);
        return ResponseEntity.ok().build();
    }
}