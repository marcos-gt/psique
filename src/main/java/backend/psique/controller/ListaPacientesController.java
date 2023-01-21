package backend.psique.controller;

import backend.psique.model.paciente.DadosListagemPaciente;
import backend.psique.model.paciente.Paciente;
import backend.psique.model.paciente.PacienteRepository;
import backend.psique.model.paciente.ServicoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ListaPacientesController {
    @Autowired
    ServicoPaciente servicoPaciente;
    @Autowired
    PacienteRepository repository;
    @GetMapping("/pacientesListar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("listpaciente");
        List<DadosListagemPaciente> pacientes = servicoPaciente.ListarTodos();
        mv.addObject("pacientes", pacientes);
        return mv;
    }

}
