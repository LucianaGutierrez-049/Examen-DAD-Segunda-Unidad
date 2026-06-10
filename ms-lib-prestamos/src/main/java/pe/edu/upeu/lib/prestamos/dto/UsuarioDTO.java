package pe.edu.upeu.lib.prestamos.dto;

public record UsuarioDTO(Long idUsuario, String nombre, String apellido, String correo, String password, String telefono, String direccion, String tipoUsuario, String estado, String fechaRegistro) {
}

