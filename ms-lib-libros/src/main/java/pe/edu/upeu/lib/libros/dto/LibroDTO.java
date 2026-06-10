package pe.edu.upeu.lib.libros.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LibroDTO(Long idLibro, @NotBlank String titulo, @NotBlank String isbn, Integer anioPublicacion, String estado, @NotNull Long idCategoria, @NotNull Long idAutor, @Min(0) Integer stockTotal, @Min(0) Integer stockDisponible) {
}

