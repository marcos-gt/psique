package backend.psique.model.usuario;

import backend.psique.model.Mensagem;
import backend.psique.model.psicologo.Psicologo;
import backend.psique.model.psicologo.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServico {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PsicologoRepository psicologoRepository;
    @Autowired
    private Mensagem mensagem;


    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    public ResponseEntity<?> cadastrar(Usuario obj) {
        System.out.println(obj.getUsername()+"Usuario!!");
        System.out.println("Pass encoder: "+passwordEncoder().encode(obj.getPassword()));
        obj.setPassword(passwordEncoder().encode(obj.getPassword()));
        repository.save(obj);
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }

    public ResponseEntity<?> validarLogin(Usuario usuario) {
        UserDetails optionalUsuario = repository.findByUsername(usuario.getUsername());
        if (optionalUsuario != null) {
            if (passwordEncoder().matches(usuario.getPassword(), optionalUsuario.getPassword())) {
                System.out.println(Optional.of(optionalUsuario));
                mensagem.setMensagem("Login realizado com sucesso");
                return new ResponseEntity<>(mensagem, HttpStatus.OK);
            } else {
                mensagem.setMensagem("Verifique os dados e tente novamente");
                return new ResponseEntity<>(mensagem, HttpStatus.UNAUTHORIZED);
            }
        } else {
            mensagem.setMensagem("Verifique os dados e tente novamente");
            return new ResponseEntity<>(mensagem, HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<?> cadastrarForm(DadosCadastroForm obj) {
        Optional<Psicologo> psicologo = psicologoRepository.findByCpf(obj.cpf());
        System.out.println("Estou testando se tem psicologo");
        if(psicologo.isEmpty()) {
            return ResponseEntity.badRequest().body("Não existe psicologo com esse cpf");
        }else {
            System.out.println("aqui tem psicologo!");
            System.out.println(psicologo.get().getNome());
            Usuario usuario = new Usuario();
            usuario.setUsername(obj.username());
            usuario.setPassword(passwordEncoder().encode(obj.password()));
            usuario.setPsicologo(psicologo.get());
            return ResponseEntity.ok(repository.save(usuario));
        }
    }
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    public UserDetails findByUsername(String username) {
    return repository.findByUsername(username);
    }
}
