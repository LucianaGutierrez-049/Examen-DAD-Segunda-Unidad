package pe.edu.upeu.lib.prestamos.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record DevolucionDTO(Long idDevolucion, @NotNull Long idPrestamo, LocalDate fechaDevolucion, String observacion, String estado) {
}

