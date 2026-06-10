package pe.edu.upeu.lib.reservas.exception;

import java.time.LocalDateTime;

public record ApiError(LocalDateTime fecha, int estado, String error, String mensaje) {
}
