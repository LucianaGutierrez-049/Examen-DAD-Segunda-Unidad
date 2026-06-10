package pe.edu.upeu.lib.prestamos.controller;

import pe.edu.upeu.lib.prestamos.dto.DevolucionDTO;
import pe.edu.upeu.lib.prestamos.dto.PrestamoDTO;
import pe.edu.upeu.lib.prestamos.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    private final PrestamoService service;
    public PrestamoController(PrestamoService service) { this.service = service; }
    @GetMapping public List<PrestamoDTO> listar() { return service.listar(); }
    @GetMapping("/{id}") public PrestamoDTO obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public PrestamoDTO crear(@Valid @RequestBody PrestamoDTO dto) { return service.crear(dto); }
    @PutMapping("/{id}") public PrestamoDTO actualizar(@PathVariable Long id, @Valid @RequestBody PrestamoDTO dto) { return service.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { service.eliminar(id); }
    @PostMapping("/devoluciones") @ResponseStatus(HttpStatus.CREATED) public DevolucionDTO devolver(@Valid @RequestBody DevolucionDTO dto) { return service.devolver(dto); }
}
