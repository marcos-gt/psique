package backend.psique.controller;

import backend.psique.model.psicologo.DadosCadastroPsicologo;
import backend.psique.model.psicologo.Psicologo;
import backend.psique.model.psicologo.PsicologoRepository;
import backend.psique.model.psicologo.PsicologoServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
public class PsiController {
    @Autowired
    private PsicologoRepository repository;
    @Autowired
    private PsicologoServico servicoPsicologo;

    @PostMapping("/cadastroPsi")
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPsicologo dados){
        servicoPsicologo.cadastro(new Psicologo(dados));
    }
    @GetMapping("/viewsPsi")
    public ModelAndView visualizar(){
        ModelAndView mv = new ModelAndView("viewsPsi");
        mv.addObject("psicologos", servicoPsicologo.listarTodos());
        return mv;
    }
}
