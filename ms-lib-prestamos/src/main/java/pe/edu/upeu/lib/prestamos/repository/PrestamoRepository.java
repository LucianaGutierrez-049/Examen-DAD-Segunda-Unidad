package pe.edu.upeu.lib.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lib.prestamos.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}

