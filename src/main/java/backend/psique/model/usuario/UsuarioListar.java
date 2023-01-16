package backend.psique.model.usuario;

import backend.psique.model.psicologo.Psicologo;

public record UsuarioListar(String email, Psicologo psicologo) {
}
