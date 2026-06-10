package pe.edu.upeu.lib.libros.controller;

import pe.edu.upeu.lib.libros.dto.CategoriaDTO;
import pe.edu.upeu.lib.libros.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService service;
    public CategoriaController(CategoriaService service) { this.service = service; }
    @GetMapping public List<CategoriaDTO> listar() { return service.listar(); }
    @GetMapping("/{id}") public CategoriaDTO obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public CategoriaDTO crear(@Valid @RequestBody CategoriaDTO dto) { return service.crear(dto); }
    @PutMapping("/{id}") public CategoriaDTO actualizar(@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto) { return service.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
