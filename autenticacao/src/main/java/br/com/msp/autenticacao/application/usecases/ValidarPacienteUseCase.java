package br.com.msp.autenticacao.application.usecases;

import br.com.msp.autenticacao.application.gateways.UsuarioGateway;
import br.com.msp.autenticacao.domain.Perfil;

public class ValidarPacienteUseCase {

    private final UsuarioGateway usuarioGateway;

    public ValidarPacienteUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public boolean existsPaciente(Long pacienteId) {
        try {
            var usuario = usuarioGateway.findById(pacienteId);

            if (usuario.isEmpty()) {
                return false;
            }

            boolean isPaciente = usuario.get().getPerfil() == Perfil.PACIENTE;

            return isPaciente;
        } catch (Exception e) {
            return false;
        }
    }
}
