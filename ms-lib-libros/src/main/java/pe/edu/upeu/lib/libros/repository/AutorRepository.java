package pe.edu.upeu.lib.libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lib.libros.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}

