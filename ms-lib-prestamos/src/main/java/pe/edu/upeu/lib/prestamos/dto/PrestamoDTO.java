package pe.edu.upeu.lib.prestamos.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record PrestamoDTO(Long idPrestamo, @NotNull Long idUsuario, LocalDate fechaPrestamo, LocalDate fechaVencimiento, LocalDate fechaDevolucion, String estado, @Valid @NotEmpty List<DetallePrestamoDTO> detalles) {
}

