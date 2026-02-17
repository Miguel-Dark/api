package med.voll.api.paciente;

import med.voll.api.direccion.Direccion;
//DTO
public record DatosRegistroPaciente(
        String nombre,
        String email,
        String telefono,
        String documentoIdentidad,
        Direccion direccion) {
}
