package backend.psique.controller;

import backend.psique.model.paciente.*;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
public class PacienteController {
    @Autowired
    private PacienteRepository repository;
    @Autowired
    private ServicoPaciente servicoPaciente;
    @PostMapping("/cadPaciente")
    @Transactional
    @PermitAll
    public ModelAndView cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        ModelAndView mv = new ModelAndView("cadPaciente");
        mv.addObject("paciente", servicoPaciente.cadastrar(new Paciente(dados)));
        return mv;
    }

    @GetMapping("/cadPaciente")
    public ModelAndView visualizar(Pageable paginacao){
        ModelAndView mv = new ModelAndView("cadPaciente");
        Page<Paciente> pacientes = repository.findAll(paginacao);
        mv.addObject("pacientes", pacientes);
        return mv;
    }
}
