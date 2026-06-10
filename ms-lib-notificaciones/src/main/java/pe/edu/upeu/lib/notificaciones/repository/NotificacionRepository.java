package pe.edu.upeu.lib.notificaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lib.notificaciones.entity.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}

