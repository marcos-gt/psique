package backend.psique.controller;

import backend.psique.model.consulta.Consulta;
import backend.psique.model.consulta.ConsultaRepository;
import backend.psique.model.consulta.DadosConsulta;
import backend.psique.model.consulta.ServicoConsulta;
import backend.psique.model.psicologo.Psicologo;
import backend.psique.model.psicologo.PsicologoServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class ConsultaController {
    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private ServicoConsulta servicoConsulta;
    @Autowired
    private PsicologoServico servicoPsicologo;
    @PostMapping("cadastrarconsulta")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosConsulta dados){
        return servicoConsulta.cadastrar(new Consulta(dados));
    }
    @GetMapping("/consultasListar")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("consultasListar");
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        List<Consulta> consultas = repository.findAll();
        List<String> datas = new ArrayList<>();
        for(Consulta c : consultas){
            String date = simpleDateFormat.format(c.getData_consulta());
            System.out.println(date);
            datas.add(date);
        }
        mv.addObject("consultas",consultas);
        mv.addObject("datas",datas);
        return mv;
    }
    @GetMapping("/cadConsulta")
    public ModelAndView cadastro(){
        ModelAndView mv = new ModelAndView("cadConsulta");
        List<Psicologo> psicologos = servicoPsicologo.listarTodos();
        mv.addObject("psicologos", psicologos);
        return mv;
    }
    @GetMapping("/listar/{cpf}")
    public List<Consulta> ListarPorCpf(@PathVariable String cpf){
        return repository.findByPaciente_Cpf(cpf);
    }
    @GetMapping("/listar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://127.0.0.1:5501/")
    public List<Consulta> Listar(){
        return servicoConsulta.listarAll();
    }
}
