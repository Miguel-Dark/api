package med.voll.api.paciente;

import med.voll.api.direccion.DatosDireccion;
//DTO
public record DatosRegistroPaciente(
        String nombre,
        String email,
        String telefono,
        String documentoIdentidad,
        DatosDireccion datosDireccion) {
}
