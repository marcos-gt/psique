package backend.psique.model.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(@NotBlank String username, @NotBlank String password) {
}
