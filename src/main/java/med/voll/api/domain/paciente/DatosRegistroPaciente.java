package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;
//DTO
public record DatosRegistroPaciente(
        @NotBlank(message = "¡El nombre no puede ir vacío!")
        String nombre,
        @NotBlank(message = "El email es vital para las notificaciones")
        @Email(message = "Ese formato de email no me convence, revísalo")
        String email,
        @NotBlank(message = "El teléfono es obligatorio")
        String telefono,
        @NotBlank(message = "El documento debe tener 10 dígitos")
        @Pattern(regexp = "\\d{10,13}", message = "El documento debe ser de exactamente 10 números")
        String documentoIdentidad,
        @NotNull(message = "La dirección no puede ser nula")
        @Valid DatosDireccion direccion) {
}
