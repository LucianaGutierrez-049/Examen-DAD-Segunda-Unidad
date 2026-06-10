package pe.edu.upeu.lib.libros.service;

import pe.edu.upeu.lib.libros.dto.AutorDTO;
import pe.edu.upeu.lib.libros.entity.Autor;
import pe.edu.upeu.lib.libros.exception.RecursoNoEncontradoException;
import pe.edu.upeu.lib.libros.repository.AutorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorService {
    private final AutorRepository repository;
    public AutorService(AutorRepository repository) { this.repository = repository; }
    public List<AutorDTO> listar() { return repository.findAll().stream().map(this::toDto).toList(); }
    public AutorDTO obtener(Long id) { return toDto(buscar(id)); }
    public AutorDTO crear(AutorDTO dto) { return toDto(repository.save(toEntity(dto, new Autor()))); }
    public AutorDTO actualizar(Long id, AutorDTO dto) { return toDto(repository.save(toEntity(dto, buscar(id)))); }
    public void eliminar(Long id) { repository.delete(buscar(id)); }
    private Autor buscar(Long id) { return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Autor no encontrado: " + id)); }
    private AutorDTO toDto(Autor a) { return new AutorDTO(a.getIdAutor(), a.getNombres(), a.getApellidos(), a.getNacionalidad()); }
    private Autor toEntity(AutorDTO dto, Autor a) { a.setNombres(dto.nombres()); a.setApellidos(dto.apellidos()); a.setNacionalidad(dto.nacionalidad()); return a; }
}
