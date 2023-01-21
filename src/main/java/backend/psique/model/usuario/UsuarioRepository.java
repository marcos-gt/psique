package backend.psique.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findAllBy();


    UserDetails findByUsername(String username);
}
