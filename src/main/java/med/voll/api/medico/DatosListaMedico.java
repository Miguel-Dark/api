package med.voll.api.medico;

public record DatosListaMedico(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad
) {
    public DatosListaMedico(Medico medico) { //constructor
        this(
                medico.getNombre(),
                medico.getEmail(),
                medico.getDocumento(),
                medico.getEspecialidad()
        );
    }
}
