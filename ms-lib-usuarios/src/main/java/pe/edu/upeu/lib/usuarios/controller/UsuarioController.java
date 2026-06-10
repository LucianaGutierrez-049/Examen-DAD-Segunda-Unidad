package pe.edu.upeu.lib.usuarios.controller;

import pe.edu.upeu.lib.usuarios.dto.UsuarioDTO;
import pe.edu.upeu.lib.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service) { this.service = service; }
    @GetMapping public List<UsuarioDTO> listar() { return service.listar(); }
    @GetMapping("/{id}") public UsuarioDTO obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public UsuarioDTO crear(@Valid @RequestBody UsuarioDTO dto) { return service.crear(dto); }
    @PutMapping("/{id}") public UsuarioDTO actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) { return service.actualizar(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
