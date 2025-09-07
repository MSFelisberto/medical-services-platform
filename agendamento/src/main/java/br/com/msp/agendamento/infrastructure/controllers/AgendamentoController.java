package br.com.msp.agendamento.infrastructure.controllers;

import br.com.msp.agendamento.application.dto.AgendarConsultaInput;
import br.com.msp.agendamento.application.dto.ReagendarConsultaInput;
import br.com.msp.agendamento.application.usecases.*;
import br.com.msp.agendamento.infrastructure.controllers.dto.ConsultaRequestDTO;
import br.com.msp.agendamento.infrastructure.controllers.dto.ConsultaResponseDTO;
import br.com.msp.agendamento.infrastructure.controllers.mappers.ConsultaDTOMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoUseCase agendamentoUseCase;
    private final ConsultaDTOMapper mapper;

    public AgendamentoController(AgendamentoUseCase agendamentoUseCase,
                                 ConsultaDTOMapper mapper) {
        this.agendamentoUseCase = agendamentoUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public ResponseEntity<ConsultaResponseDTO> agendar(
            @RequestBody @Valid ConsultaRequestDTO requestDTO
    ) {
        AgendarConsultaInput input = mapper.toInput(requestDTO);
        var output = agendamentoUseCase.agendarConsulta(input);
        var response = mapper.toResponse(output);
        URI uri = URI.create("/agendamento/" + response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public ResponseEntity<ConsultaResponseDTO> reagendar(
            @PathVariable Long id,
            @RequestBody @Valid ConsultaRequestDTO requestDTO
    ) {
        ReagendarConsultaInput input = mapper.toReagendarInput(id, requestDTO);
        var output = agendamentoUseCase.reagendarConsulta(input);
        return ResponseEntity.ok(mapper.toResponse(output));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        agendamentoUseCase.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/{pacienteId}")
    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO', 'PACIENTE')")
    public ResponseEntity<List<ConsultaResponseDTO>> listarPorPaciente(
            @PathVariable Long pacienteId
    ) {
        List<ConsultaResponseDTO> response = agendamentoUseCase.listarConsultasPorPaciente(pacienteId)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
