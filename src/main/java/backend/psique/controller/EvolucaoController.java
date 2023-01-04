package backend.psique.controller;

import backend.psique.model.evolucao.DadosCadastroEvolucao;
import backend.psique.model.evolucao.Evolucao;
import backend.psique.model.evolucao.EvolucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("evolucao")
public class EvolucaoController {
    @Autowired
   private EvolucaoRepository repository;
    public void cadastrar(@RequestBody DadosCadastroEvolucao dados) {
        repository.save(new Evolucao(dados));

    }
}
