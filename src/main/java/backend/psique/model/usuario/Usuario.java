package backend.psique.model.usuario;

import backend.psique.model.psicologo.Psicologo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String senha;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "cpf_psicologo",referencedColumnName = "cpf")
    private Psicologo psicologo;

    public Usuario(Usuario usuario) {
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.psicologo = usuario.getPsicologo();
    }
}
