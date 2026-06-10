package pe.edu.upeu.lib.libros.exception;

import java.time.LocalDateTime;

public record ApiError(LocalDateTime fecha, int estado, String error, String mensaje) {
}
