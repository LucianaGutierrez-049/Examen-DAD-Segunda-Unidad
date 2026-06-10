package pe.edu.upeu.lib.prestamos.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.lib.prestamos.dto.DetallePrestamoDTO;
import pe.edu.upeu.lib.prestamos.entity.DetallePrestamo;
import pe.edu.upeu.lib.prestamos.entity.Prestamo;
import pe.edu.upeu.lib.prestamos.exception.RecursoNoEncontradoException;
import pe.edu.upeu.lib.prestamos.repository.DetallePrestamoRepository;
import pe.edu.upeu.lib.prestamos.repository.PrestamoRepository;

import java.util.List;

@Service
public class DetallePrestamoService {
    private final DetallePrestamoRepository repository;
    private final PrestamoRepository prestamoRepository;

    public DetallePrestamoService(DetallePrestamoRepository repository, PrestamoRepository prestamoRepository) {
        this.repository = repository;
        this.prestamoRepository = prestamoRepository;
    }

    public List<DetallePrestamoDTO> listar() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public DetallePrestamoDTO obtener(Long id) {
        return toDto(buscar(id));
    }

    public DetallePrestamoDTO crear(DetallePrestamoDTO dto) {
        return toDto(repository.save(toEntity(dto, new DetallePrestamo())));
    }

    public DetallePrestamoDTO actualizar(Long id, DetallePrestamoDTO dto) {
        return toDto(repository.save(toEntity(dto, buscar(id))));
    }

    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }

    private DetallePrestamo buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Detalle de prestamo no encontrado: " + id));
    }

    private Prestamo prestamo(Long id) {
        return prestamoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Prestamo no encontrado: " + id));
    }

    private DetallePrestamoDTO toDto(DetallePrestamo detalle) {
        Long idPrestamo = detalle.getPrestamo() == null ? null : detalle.getPrestamo().getIdPrestamo();
        return new DetallePrestamoDTO(detalle.getIdDetalle(), idPrestamo, detalle.getIdLibro(), detalle.getCantidad());
    }

    private DetallePrestamo toEntity(DetallePrestamoDTO dto, DetallePrestamo detalle) {
        detalle.setPrestamo(prestamo(dto.idPrestamo()));
        detalle.setIdLibro(dto.idLibro());
        detalle.setCantidad(dto.cantidad());
        return detalle;
    }
}
