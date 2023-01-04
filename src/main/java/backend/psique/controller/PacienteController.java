package backend.psique.controller;

import backend.psique.model.paciente.DadosCadastroPaciente;
import backend.psique.model.paciente.Paciente;
import backend.psique.model.paciente.PacienteRepository;
import backend.psique.model.paciente.DadosListagemPaciente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("paciente")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        System.out.println(dados);
        Paciente paciente = new Paciente(dados);
        System.out.println("Paciente: "+dados.endereco().getLogradouro());
        repository.save(paciente);
    }
    @GetMapping
    public List<DadosListagemPaciente> visualizar(){
        return repository.findAll().stream().map(DadosListagemPaciente::new).toList();
    }
}
