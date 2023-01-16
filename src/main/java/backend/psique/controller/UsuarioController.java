package backend.psique.controller;

import backend.psique.model.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController

public class UsuarioController {
    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;
    @Autowired
    private UsuarioServico servico;

    public UsuarioController(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/cadUser")
    @Transactional

    public ResponseEntity<?> cadastrarUsuario(@RequestBody DadosCadastroUsuario user) {
        return servico.cadastrar(new Usuario(user));
    }
    @GetMapping("/usuariosListar")
    public ModelAndView listarUsuarios() {
        ModelAndView mv = new ModelAndView("usuariosListar");
        List<Usuario> usuarios = repository.findAllBy();
        mv.addObject("usuarios", usuarios);
        return mv;
    }
    @GetMapping("/loginUser")
    public ResponseEntity<?> LoginUsuario(@RequestBody Usuario usuario) {
        System.out.println(usuario);
        return servico.validarLogin(usuario);
    }
}