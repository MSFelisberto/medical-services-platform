package br.com.msp.historico.infrastructure.controllers;

import br.com.msp.historico.application.dto.HistoricoDTO;
import br.com.msp.historico.application.usecase.HistoricoUseCase;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class HistoricoController {
    private final HistoricoUseCase historicoUseCase;

    public HistoricoController(HistoricoUseCase historicoUseCase) {
        this.historicoUseCase = historicoUseCase;
    }

    @QueryMapping
    public List<HistoricoDTO> historicoPorPaciente(@Argument Long pacienteId) {
        return historicoUseCase.buscarPorPacienteId(pacienteId);
    }
}
