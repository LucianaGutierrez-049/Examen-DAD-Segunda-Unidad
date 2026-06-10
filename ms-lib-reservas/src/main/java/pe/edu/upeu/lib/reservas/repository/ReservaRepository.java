package pe.edu.upeu.lib.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lib.reservas.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}

