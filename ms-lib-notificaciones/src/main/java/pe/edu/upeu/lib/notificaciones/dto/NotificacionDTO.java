package pe.edu.upeu.lib.notificaciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record NotificacionDTO(Long idNotificacion, @NotNull Long idUsuario, @NotBlank String titulo, @NotBlank String mensaje, String tipo, LocalDateTime fechaEnvio, String estado) {
}

