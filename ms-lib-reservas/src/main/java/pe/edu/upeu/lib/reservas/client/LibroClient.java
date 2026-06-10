package pe.edu.upeu.lib.reservas.client;

import pe.edu.upeu.lib.reservas.dto.LibroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-lib-libros")
public interface LibroClient { @GetMapping("/libros/{id}") LibroDTO obtener(@PathVariable Long id); }

