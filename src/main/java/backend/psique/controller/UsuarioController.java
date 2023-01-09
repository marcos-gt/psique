package backend.psique.controller;

import backend.psique.model.usuario.Usuario;
import backend.psique.model.usuario.UsuarioServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @PostMapping("/cadUser")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
        return new UsuarioServico().cadastrar(usuario);
    }
    public ResponseEntity<?> LoginUsuario(@RequestBody Usuario usuario) {
        return new UsuarioServico().validarLogin(usuario);
    }
}