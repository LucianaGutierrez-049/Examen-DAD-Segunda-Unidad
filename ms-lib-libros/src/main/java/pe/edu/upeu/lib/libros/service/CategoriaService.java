package pe.edu.upeu.lib.libros.service;

import pe.edu.upeu.lib.libros.dto.CategoriaDTO;
import pe.edu.upeu.lib.libros.entity.Categoria;
import pe.edu.upeu.lib.libros.exception.RecursoNoEncontradoException;
import pe.edu.upeu.lib.libros.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;
    public CategoriaService(CategoriaRepository repository) { this.repository = repository; }
    public List<CategoriaDTO> listar() { return repository.findAll().stream().map(this::toDto).toList(); }
    public CategoriaDTO obtener(Long id) { return toDto(buscar(id)); }
    public CategoriaDTO crear(CategoriaDTO dto) { return toDto(repository.save(toEntity(dto, new Categoria()))); }
    public CategoriaDTO actualizar(Long id, CategoriaDTO dto) { return toDto(repository.save(toEntity(dto, buscar(id)))); }
    public void eliminar(Long id) { repository.delete(buscar(id)); }
    private Categoria buscar(Long id) { return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Categoria no encontrado: " + id)); }
    private CategoriaDTO toDto(Categoria c) { return new CategoriaDTO(c.getIdCategoria(), c.getNombre(), c.getDescripcion()); }
    private Categoria toEntity(CategoriaDTO dto, Categoria c) { c.setNombre(dto.nombre()); c.setDescripcion(dto.descripcion()); return c; }
}
