package pe.edu.upeu.lib.reservas.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ReservaDTO(Long idReserva, @NotNull Long idUsuario, @NotNull Long idLibro, LocalDateTime fechaReserva, LocalDateTime fechaExpiracion, String estado) {
}

