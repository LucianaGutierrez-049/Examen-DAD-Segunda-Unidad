package pe.edu.upeu.lib.prestamos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetallePrestamoDTO(Long idDetalle, Long idPrestamo, @NotNull Long idLibro, @Min(1) Integer cantidad) {
}

