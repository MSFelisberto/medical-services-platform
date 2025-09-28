package br.com.msp.autenticacao.application.services;

import br.com.msp.autenticacao.application.dto.*;
import br.com.msp.autenticacao.application.ports.inbound.PacienteUseCase;
import br.com.msp.autenticacao.application.ports.outbound.*;
import br.com.msp.autenticacao.domain.paciente.exception.*;
import br.com.msp.autenticacao.domain.paciente.model.*;
import br.com.msp.autenticacao.domain.shared.model.*;

public class PacienteUseCaseImpl implements PacienteUseCase {

    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;

    public PacienteUseCaseImpl(PacienteRepository pacienteRepository, PasswordEncoder passwordEncoder) {
        this.pacienteRepository = pacienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PacienteOutput cadastrarPaciente(CadastrarPacienteCommand command) {
        Email email = new Email(command.email());

        // Validar se já existe
        if (pacienteRepository.existsByEmail(email)) {
            throw new PacienteBusinessException("Já existe um paciente com este e-mail: " + email.getValue());
        }

        if (pacienteRepository.existsByCpf(command.cpf())) {
            throw new PacienteBusinessException("Já existe um paciente com este CPF");
        }

        Senha senha = new Senha(command.senha());
        Endereco endereco = new Endereco(
                command.endereco().logradouro(),
                command.endereco().numero(),
                command.endereco().complemento(),
                command.endereco().bairro(),
                command.endereco().cidade(),
                command.endereco().estado(),
                command.endereco().cep()
        );

        Paciente paciente = new Paciente(
                email,
                senha,
                command.nomeCompleto(),
                command.cpf(),
                command.dataNascimento(),
                command.telefone(),
                endereco
        );

        Paciente pacienteSalvo = pacienteRepository.save(paciente);
        return mapToOutput(pacienteSalvo);
    }

    @Override
    public boolean validarPacienteExiste(ValidarPacienteQuery query) {
        try {
            PacienteId pacienteId = new PacienteId(query.pacienteId());
            return pacienteRepository.findById(pacienteId).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public PacienteOutput buscarPorId(Long id) {
        PacienteId pacienteId = new PacienteId(id);
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + id));

        return mapToOutput(paciente);
    }

    private PacienteOutput mapToOutput(Paciente paciente) {
        EnderecoDTO enderecoDTO = new EnderecoDTO(
                paciente.getEndereco().getLogradouro(),
                paciente.getEndereco().getNumero(),
                paciente.getEndereco().getComplemento(),
                paciente.getEndereco().getBairro(),
                paciente.getEndereco().getCidade(),
                paciente.getEndereco().getEstado(),
                paciente.getEndereco().getCep()
        );

        return new PacienteOutput(
                paciente.getId().getValue(),
                paciente.getEmail().getValue(),
                paciente.getNomeCompleto(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getTelefone(),
                enderecoDTO,
                paciente.isAtivo()
        );
    }
}