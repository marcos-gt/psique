package backend.psique.model.consulta;

import backend.psique.model.paciente.Paciente;
import backend.psique.model.psicologo.Psicologo;
import jakarta.validation.constraints.NotBlank;

public record DadosConsulta(
        Psicologo psicologo,
        Paciente paciente,
        String data_consulta,
        @NotBlank
        String valor) {
}
