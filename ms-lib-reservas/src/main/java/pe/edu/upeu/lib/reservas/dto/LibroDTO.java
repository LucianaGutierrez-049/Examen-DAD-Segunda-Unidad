package pe.edu.upeu.lib.reservas.dto;

public record LibroDTO(Long idLibro, String titulo, String isbn, Integer anioPublicacion, String estado, Long idCategoria, Long idAutor, Integer stockTotal, Integer stockDisponible) {
}

