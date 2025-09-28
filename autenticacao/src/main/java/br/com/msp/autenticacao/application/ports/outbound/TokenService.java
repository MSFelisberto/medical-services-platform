package br.com.msp.autenticacao.application.ports.outbound;

import br.com.msp.autenticacao.domain.paciente.model.Paciente;
import br.com.msp.autenticacao.domain.funcionario.model.Funcionario;

public interface TokenService {
    String generateTokenForPaciente(Paciente paciente);
    String generateTokenForFuncionario(Funcionario funcionario);
    boolean validateToken(String token);
    String getEmailFromToken(String token);
    Long getUserIdFromToken(String token);
    String getUserTypeFromToken(String token);
}