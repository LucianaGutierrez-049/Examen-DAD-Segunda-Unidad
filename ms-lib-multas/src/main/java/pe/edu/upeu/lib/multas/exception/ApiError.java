package pe.edu.upeu.lib.multas.exception;

import java.time.LocalDateTime;

public record ApiError(LocalDateTime fecha, int estado, String error, String mensaje) {
}
