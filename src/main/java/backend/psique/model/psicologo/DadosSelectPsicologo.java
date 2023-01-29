package backend.psique.model.psicologo;

public record DadosSelectPsicologo(String cpf, String nome) {
    public DadosSelectPsicologo(Psicologo psicologo) {
        this(psicologo.getCpf(), psicologo.getNome());
    }

}
