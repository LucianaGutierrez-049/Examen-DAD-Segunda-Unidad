package pe.edu.upeu.lib.libros.controller;

import pe.edu.upeu.lib.libros.dto.AutorDTO;
import pe.edu.upeu.lib.libros.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {
    private final AutorService service;
    public AutorController(AutorService service) { this.service = service; }
    @GetMapping public List<AutorDTO> listar() { return service.listar(); }
    @GetMapping("/{id}") public AutorDTO obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public AutorDTO crear(@Valid @RequestBody AutorDTO dto) { return service.crear(dto); }
    @PutMapping("/{id}") public AutorDTO actualizar(@PathVariable Long id, @Valid @RequestBody AutorDTO dto) { return service.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
