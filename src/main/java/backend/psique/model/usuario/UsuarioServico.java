package backend.psique.model.usuario;

import backend.psique.model.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServico {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private Mensagem mensagem;
    @Autowired
    private PasswordEncoder encoder;
    public ResponseEntity<?>cadastrar(Usuario user){
        user.setSenha(encoder.encode(user.getSenha()));
        repository.save(user);
        return new ResponseEntity<>(mensagem,HttpStatus.CREATED);
    }

    public ResponseEntity<?> validarLogin(Usuario usuario) {
        Optional<Usuario> optionalUsuario = repository.findByEmail(usuario.getEmail());
        if(optionalUsuario.isPresent()){
            if(encoder.matches(usuario.getSenha(),optionalUsuario.get().getSenha())){
                mensagem.setMensagem("Login realizado com sucesso");
                return new ResponseEntity<>(mensagem,HttpStatus.OK);
            }else{
                mensagem.setMensagem("Verifique os dados e tente novamente");
                return new ResponseEntity<>(mensagem,HttpStatus.UNAUTHORIZED);
            }
    }else {
            mensagem.setMensagem("Verifique os dados e tente novamente");
            return new ResponseEntity<>(mensagem,HttpStatus.UNAUTHORIZED);
        }
    }
}
