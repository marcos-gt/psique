package backend.psique.model.psicologo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PsicologoRepository extends JpaRepository<Psicologo, String> {

    Optional<Psicologo> findByCpf(String cpf);

    @Query(value = "SELECT COUNT(*) FROM consulta WHERE cpf_psicologo = ?cpf AND MONTH(data) = MONTH(CURRENT_DATE())", nativeQuery = true)
    double CountConsultasMes(String cpf);
}
