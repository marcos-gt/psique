package backend.psique.model.paciente;

import backend.psique.model.endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPaciente(
        @NotBlank
        String cpf,
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        String evolucao,
        @NotNull @Valid
        Endereco endereco) {
}
