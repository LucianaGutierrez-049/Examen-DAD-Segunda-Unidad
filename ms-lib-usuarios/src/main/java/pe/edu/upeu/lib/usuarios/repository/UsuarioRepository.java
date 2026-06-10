package pe.edu.upeu.lib.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lib.usuarios.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

