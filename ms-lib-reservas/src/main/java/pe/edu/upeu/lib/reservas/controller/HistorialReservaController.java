package pe.edu.upeu.lib.reservas.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lib.reservas.dto.HistorialReservaDTO;
import pe.edu.upeu.lib.reservas.service.HistorialReservaService;

import java.util.List;

@RestController
@RequestMapping("/historial-reservas")
public class HistorialReservaController {
    private final HistorialReservaService service;

    public HistorialReservaController(HistorialReservaService service) {
        this.service = service;
    }

    @GetMapping public List<HistorialReservaDTO> listar() { return service.listar(); }
    @GetMapping("/{id}") public HistorialReservaDTO obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public HistorialReservaDTO crear(@Valid @RequestBody HistorialReservaDTO dto) { return service.crear(dto); }
    @PutMapping("/{id}") public HistorialReservaDTO actualizar(@PathVariable Long id, @Valid @RequestBody HistorialReservaDTO dto) { return service.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
