package backend.psique.model.paciente;

import backend.psique.model.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServicoPaciente {
    @Autowired
    private Mensagem mensagem;
    @Autowired
    private PacienteRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ResponseEntity<?>cadastrar(Paciente obj){
        if(repository.CountByCpf(obj.getCpf()) > 1 ) {
            mensagem.setMensagem("CPF já cadastrado");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else {
            repository.save(obj);
            mensagem.setMensagem("Paciente cadastrado com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
        }
    }
}