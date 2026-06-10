package pe.edu.upeu.lib.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lib.prestamos.entity.DetallePrestamo;

public interface DetallePrestamoRepository extends JpaRepository<DetallePrestamo, Long> {
}
