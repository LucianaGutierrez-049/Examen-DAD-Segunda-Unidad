package pe.edu.upeu.lib.libros.service;

import pe.edu.upeu.lib.libros.dto.LibroDTO;
import pe.edu.upeu.lib.libros.entity.Autor;
import pe.edu.upeu.lib.libros.entity.Categoria;
import pe.edu.upeu.lib.libros.entity.Libro;
import pe.edu.upeu.lib.libros.exception.RecursoNoEncontradoException;
import pe.edu.upeu.lib.libros.exception.ReglaNegocioException;
import pe.edu.upeu.lib.libros.repository.AutorRepository;
import pe.edu.upeu.lib.libros.repository.CategoriaRepository;
import pe.edu.upeu.lib.libros.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LibroService {
    private final LibroRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final AutorRepository autorRepository;
    public LibroService(LibroRepository repository, CategoriaRepository categoriaRepository, AutorRepository autorRepository) { this.repository = repository; this.categoriaRepository = categoriaRepository; this.autorRepository = autorRepository; }
    public List<LibroDTO> listar() { return repository.findAll().stream().map(this::toDto).toList(); }
    public LibroDTO obtener(Long id) { return toDto(buscar(id)); }
    public LibroDTO crear(LibroDTO dto) { return toDto(repository.save(toEntity(dto, new Libro()))); }
    public LibroDTO actualizar(Long id, LibroDTO dto) { return toDto(repository.save(toEntity(dto, buscar(id)))); }
    public void eliminar(Long id) { repository.delete(buscar(id)); }
    @Transactional
    public LibroDTO descontarStock(Long id, int cantidad) { Libro libro = buscar(id); if (libro.getStockDisponible() == null || libro.getStockDisponible() < cantidad) throw new ReglaNegocioException("Stock insuficiente para el libro: " + id); libro.setStockDisponible(libro.getStockDisponible() - cantidad); return toDto(libro); }
    @Transactional
    public LibroDTO aumentarStock(Long id, int cantidad) { Libro libro = buscar(id); int disponible = libro.getStockDisponible() == null ? 0 : libro.getStockDisponible(); libro.setStockDisponible(disponible + cantidad); return toDto(libro); }
    private Libro buscar(Long id) { return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Libro no encontrado: " + id)); }
    private Categoria categoria(Long id) { return categoriaRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Categoria no encontrada: " + id)); }
    private Autor autor(Long id) { return autorRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Autor no encontrado: " + id)); }
    private LibroDTO toDto(Libro l) { return new LibroDTO(l.getIdLibro(), l.getTitulo(), l.getIsbn(), l.getAnioPublicacion(), l.getEstado(), l.getCategoria() == null ? null : l.getCategoria().getIdCategoria(), l.getAutor() == null ? null : l.getAutor().getIdAutor(), l.getStockTotal(), l.getStockDisponible()); }
    private Libro toEntity(LibroDTO dto, Libro l) { l.setTitulo(dto.titulo()); l.setIsbn(dto.isbn()); l.setAnioPublicacion(dto.anioPublicacion()); l.setEstado(dto.estado()); l.setCategoria(categoria(dto.idCategoria())); l.setAutor(autor(dto.idAutor())); l.setStockTotal(dto.stockTotal()); l.setStockDisponible(dto.stockDisponible()); return l; }
}
