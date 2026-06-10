package pe.edu.upeu.lib.prestamos.dto;

public record NotificacionRequest(Long idUsuario, String titulo, String mensaje, String tipo) {
}

