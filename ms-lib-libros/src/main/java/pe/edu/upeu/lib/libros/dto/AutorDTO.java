package pe.edu.upeu.lib.libros.dto;

import jakarta.validation.constraints.NotBlank;

public record AutorDTO(Long idAutor, @NotBlank String nombres, @NotBlank String apellidos, String nacionalidad) {
}

