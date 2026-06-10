package pe.edu.upeu.lib.reservas.client;

import pe.edu.upeu.lib.reservas.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-lib-usuarios")
public interface UsuarioClient { @GetMapping("/usuarios/{id}") UsuarioDTO obtener(@PathVariable Long id); }

