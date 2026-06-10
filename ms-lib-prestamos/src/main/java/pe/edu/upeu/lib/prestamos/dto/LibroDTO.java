package pe.edu.upeu.lib.prestamos.dto;

public record LibroDTO(Long idLibro, String titulo, String isbn, Integer anioPublicacion, String estado, Long idCategoria, Long idAutor, Integer stockTotal, Integer stockDisponible) {
}

