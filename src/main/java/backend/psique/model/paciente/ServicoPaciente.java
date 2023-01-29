package backend.psique.model.paciente;

import backend.psique.model.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicoPaciente {
    @Autowired
    private Mensagem mensagem;
    @Autowired
    private PacienteRepository repository;
    public ResponseEntity<?>cadastrar(Paciente obj){
        Optional<Paciente> paciente = repository.findByCpf(obj.getCpf());
        if(paciente.isPresent() ) {
            mensagem.setMensagem("CPF já cadastrado");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else {
            repository.save(obj);
            mensagem.setMensagem("Paciente cadastrado com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
        }
    }

    public List<DadosListagemPaciente> ListarTodos() {
        Iterable<Paciente> pacientesIterable = repository.findAll();
        return Streamable.of(pacientesIterable).map(DadosListagemPaciente::new).toList();
    }

    public List<Paciente> findAllSelect() {
        List<Paciente> pacientes = repository.findAll();
        return pacientes;
    }
}