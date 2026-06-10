package pe.edu.upeu.lib.libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lib.libros.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

