package pe.edu.upeu.lib.libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lib.libros.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}

