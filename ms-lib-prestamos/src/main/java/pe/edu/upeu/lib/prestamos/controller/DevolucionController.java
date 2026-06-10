package pe.edu.upeu.lib.prestamos.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lib.prestamos.dto.DevolucionDTO;
import pe.edu.upeu.lib.prestamos.service.DevolucionService;

import java.util.List;

@RestController
@RequestMapping("/devoluciones")
public class DevolucionController {
    private final DevolucionService service;

    public DevolucionController(DevolucionService service) {
        this.service = service;
    }

    @GetMapping public List<DevolucionDTO> listar() { return service.listar(); }
    @GetMapping("/{id}") public DevolucionDTO obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public DevolucionDTO crear(@Valid @RequestBody DevolucionDTO dto) { return service.crear(dto); }
    @PutMapping("/{id}") public DevolucionDTO actualizar(@PathVariable Long id, @Valid @RequestBody DevolucionDTO dto) { return service.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
