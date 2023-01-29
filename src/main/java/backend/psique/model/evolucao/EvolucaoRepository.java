package backend.psique.model.evolucao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
public interface EvolucaoRepository extends JpaRepository<Evolucao, Long> {
    //@Query("SELECT e FROM Evolucao e WHERE e.paciente.cpf = :cpf ORDER BY e.data_evolucao DESC")
    //Page<Evolucao> findByPaciente_CpfOrderByData_evolucaoDesc(String cpf, Pageable pageable);

    Page<Evolucao> findByPaciente_Cpf(String cpf, Pageable pageable);

}
