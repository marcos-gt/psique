package backend.psique.model.paciente;

public record DadosSelect(String cpf, String nome) {
    public DadosSelect(Paciente paciente) {
        this(paciente.getCpf(), paciente.getNome());
    }

}
