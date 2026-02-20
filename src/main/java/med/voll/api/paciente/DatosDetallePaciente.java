package med.voll.api.paciente;

import med.voll.api.direccion.Direccion;

public record DatosDetallePaciente(
        Long id,
        String nombre,
        String email,
        String documentoIdentidad,
        String telefono,
        Direccion direccion
) {
    public DatosDetallePaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getDocumentoIdentidad(),
                paciente.getTelefono(),
                paciente.getDireccion()
        );
    }
}
