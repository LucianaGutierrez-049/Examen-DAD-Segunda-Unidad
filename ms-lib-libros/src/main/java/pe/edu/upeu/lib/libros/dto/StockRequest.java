package pe.edu.upeu.lib.libros.dto;

import jakarta.validation.constraints.Min;

public record StockRequest(@Min(1) int cantidad) {
}

