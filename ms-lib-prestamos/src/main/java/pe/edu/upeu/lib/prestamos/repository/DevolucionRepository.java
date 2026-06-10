package pe.edu.upeu.lib.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lib.prestamos.entity.Devolucion;

public interface DevolucionRepository extends JpaRepository<Devolucion, Long> {
}

