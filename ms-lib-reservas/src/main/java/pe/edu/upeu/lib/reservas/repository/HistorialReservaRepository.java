package pe.edu.upeu.lib.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lib.reservas.entity.HistorialReserva;

public interface HistorialReservaRepository extends JpaRepository<HistorialReserva, Long> {
}
