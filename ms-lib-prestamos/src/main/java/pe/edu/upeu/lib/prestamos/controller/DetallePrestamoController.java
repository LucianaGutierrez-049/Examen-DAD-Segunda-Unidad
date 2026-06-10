package pe.edu.upeu.lib.prestamos.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lib.prestamos.dto.DetallePrestamoDTO;
import pe.edu.upeu.lib.prestamos.service.DetallePrestamoService;

import java.util.List;

@RestController
@RequestMapping("/detalle-prestamos")
public class DetallePrestamoController {
    private final DetallePrestamoService service;

    public DetallePrestamoController(DetallePrestamoService service) {
        this.service = service;
    }

    @GetMapping public List<DetallePrestamoDTO> listar() { return service.listar(); }
    @GetMapping("/{id}") public DetallePrestamoDTO obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public DetallePrestamoDTO crear(@Valid @RequestBody DetallePrestamoDTO dto) { return service.crear(dto); }
    @PutMapping("/{id}") public DetallePrestamoDTO actualizar(@PathVariable Long id, @Valid @RequestBody DetallePrestamoDTO dto) { return service.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
