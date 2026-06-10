package pe.edu.upeu.lib.libros.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(Long idCategoria, @NotBlank String nombre, String descripcion) {
}

