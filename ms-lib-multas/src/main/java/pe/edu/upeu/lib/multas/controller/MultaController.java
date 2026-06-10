package pe.edu.upeu.lib.multas.controller;

import pe.edu.upeu.lib.multas.dto.MultaDTO;
import pe.edu.upeu.lib.multas.service.MultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/multas")
public class MultaController {
    private final MultaService service;
    public MultaController(MultaService service) { this.service = service; }
    @GetMapping public List<MultaDTO> listar() { return service.listar(); }
    @GetMapping("/{id}") public MultaDTO obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public MultaDTO crear(@Valid @RequestBody MultaDTO dto) { return service.crear(dto); }
    @PutMapping("/{id}") public MultaDTO actualizar(@PathVariable Long id, @Valid @RequestBody MultaDTO dto) { return service.actualizar(id, dto); }
    @PutMapping("/{id}/pagar") public MultaDTO pagar(@PathVariable Long id) { return service.pagar(id); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
