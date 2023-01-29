package backend.psique.model.evolucao;

import backend.psique.model.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class ServicoEvolucao {
    @Autowired
    private Mensagem mensagem;
    @Autowired
    private EvolucaoRepository repository;


    public ResponseEntity<?>cadastrar(Evolucao obj){
        if(obj.getDescricao() == null) {
            mensagem.setMensagem("Descrição não pode ser nula");
            return ResponseEntity.badRequest().body(mensagem);
        }else if(obj.getDescricao().length() < 10) {
            mensagem.setMensagem("Descrição deve ter no mínimo 10 caracteres");
            return ResponseEntity.badRequest().body(mensagem);
        }else
            repository.save(obj);
            mensagem.setMensagem("Evolução cadastrada com sucesso");
            return ResponseEntity.ok(mensagem);
    }

    public Page<Evolucao> findByCpf(String cpf,Pageable pageable) {
        return repository.findByPaciente_Cpf(cpf,pageable);
    }
}
