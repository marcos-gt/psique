package backend.psique.model.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface PacienteRepository extends JpaRepository<Paciente, String> {


    Paciente findByCpf(String cpf);

    @Query("SELECT p FROM Paciente p WHERE p.cpf = :cpf")
    int CountByCpf(String cpf);
}
