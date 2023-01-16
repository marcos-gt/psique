package backend.psique.model.psicologo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsicologoServico {
    @Autowired
    private PsicologoRepository repository;

    public List<Psicologo> listarTodos(){
        return repository.findAll();
    }

    public void cadastro(Psicologo psicologo) {
        psicologo.setSalario(repository.CountConsultasMes(psicologo.getCpf()));
        repository.save(psicologo);
    }
}
