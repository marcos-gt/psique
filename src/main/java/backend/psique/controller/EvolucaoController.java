package backend.psique.controller;

import backend.psique.model.evolucao.DadosCadastroEvolucao;
import backend.psique.model.evolucao.Evolucao;
import backend.psique.model.evolucao.EvolucaoRepository;
import backend.psique.model.evolucao.ServicoEvolucao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping
public class EvolucaoController {
    @Autowired
   private EvolucaoRepository repository;
    @Autowired
    private ServicoEvolucao servicoEvolucao;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroEvolucao dados ) {
        servicoEvolucao.cadastrar(new Evolucao(dados));
    }
    @GetMapping("/cadastrar/{cpf}")
        public String cadastrar(@PathVariable String cpf){
        return "Evolução cadastrada com sucesso "+cpf;
        }
        @GetMapping("/evolucoesListar")
    public ModelAndView listar(Pageable pageable) {
            ModelAndView mv = new ModelAndView("evolucoesListar");
            Page<Evolucao> listarTodos = (Page<Evolucao>) servicoEvolucao.listarTodos();
            mv.addObject("evolucoes", listarTodos);
        return mv;
    }
    @GetMapping("/evolucoesListar/{cpf}")
    public ResponseEntity<?> listar(@PathVariable String cpf){
        return ResponseEntity.ok(servicoEvolucao.listarUnico(cpf));
    }
}
