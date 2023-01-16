package backend.psique.model.evolucao;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface EvolucaoRepository extends JpaRepositoryImplementation<Evolucao, Long> {

    Evolucao findByPaciente_Cpf(String cpf);
}
