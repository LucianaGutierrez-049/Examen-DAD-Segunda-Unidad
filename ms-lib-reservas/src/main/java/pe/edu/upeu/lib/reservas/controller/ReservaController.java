package pe.edu.upeu.lib.reservas.controller;

import pe.edu.upeu.lib.reservas.dto.ReservaDTO;
import pe.edu.upeu.lib.reservas.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservaService service;
    public ReservaController(ReservaService service) { this.service = service; }
    @GetMapping public List<ReservaDTO> listar() { return service.listar(); }
    @GetMapping("/{id}") public ReservaDTO obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public ReservaDTO crear(@Valid @RequestBody ReservaDTO dto) { return service.crear(dto); }
    @PutMapping("/{id}") public ReservaDTO actualizar(@PathVariable Long id, @Valid @RequestBody ReservaDTO dto) { return service.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
