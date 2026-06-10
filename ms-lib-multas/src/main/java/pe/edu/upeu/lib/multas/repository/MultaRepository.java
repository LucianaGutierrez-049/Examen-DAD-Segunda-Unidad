package pe.edu.upeu.lib.multas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lib.multas.entity.Multa;

public interface MultaRepository extends JpaRepository<Multa, Long> {
}

