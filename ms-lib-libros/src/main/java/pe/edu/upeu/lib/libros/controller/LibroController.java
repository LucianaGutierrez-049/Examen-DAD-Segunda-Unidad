package pe.edu.upeu.lib.libros.controller;

import pe.edu.upeu.lib.libros.dto.LibroDTO;
import pe.edu.upeu.lib.libros.dto.StockRequest;
import pe.edu.upeu.lib.libros.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {
    private final LibroService service;
    public LibroController(LibroService service) { this.service = service; }
    @GetMapping public List<LibroDTO> listar() { return service.listar(); }
    @GetMapping("/{id}") public LibroDTO obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public LibroDTO crear(@Valid @RequestBody LibroDTO dto) { return service.crear(dto); }
    @PutMapping("/{id}") public LibroDTO actualizar(@PathVariable Long id, @Valid @RequestBody LibroDTO dto) { return service.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { service.eliminar(id); }
    @PutMapping("/{id}/stock/descontar") public LibroDTO descontar(@PathVariable Long id, @Valid @RequestBody StockRequest request) { return service.descontarStock(id, request.cantidad()); }
    @PutMapping("/{id}/stock/aumentar") public LibroDTO aumentar(@PathVariable Long id, @Valid @RequestBody StockRequest request) { return service.aumentarStock(id, request.cantidad()); }
}
