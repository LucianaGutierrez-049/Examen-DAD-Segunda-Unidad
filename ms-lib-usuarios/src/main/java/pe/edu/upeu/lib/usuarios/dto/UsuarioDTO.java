package pe.edu.upeu.lib.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record UsuarioDTO(Long idUsuario, @NotBlank String nombre, @NotBlank String apellido, @Email @NotBlank String correo,
                         String password, String telefono, String direccion, String tipoUsuario, String estado,
                         LocalDateTime fechaRegistro) {
}
