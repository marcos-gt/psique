package backend.psique.model.usuario;

import backend.psique.model.psicologo.Psicologo;

public record DadosCadastroUsuario(String username, String password, Psicologo psicologo) {

}
