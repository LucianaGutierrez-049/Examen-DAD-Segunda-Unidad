package pe.edu.upeu.lib.notificaciones.controller;

import pe.edu.upeu.lib.notificaciones.dto.NotificacionDTO;
import pe.edu.upeu.lib.notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    private final NotificacionService service;
    public NotificacionController(NotificacionService service) { this.service = service; }
    @GetMapping public List<NotificacionDTO> listar() { return service.listar(); }
    @GetMapping("/{id}") public NotificacionDTO obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public NotificacionDTO crear(@Valid @RequestBody NotificacionDTO dto) { return service.crear(dto); }
    @PutMapping("/{id}") public NotificacionDTO actualizar(@PathVariable Long id, @Valid @RequestBody NotificacionDTO dto) { return service.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
