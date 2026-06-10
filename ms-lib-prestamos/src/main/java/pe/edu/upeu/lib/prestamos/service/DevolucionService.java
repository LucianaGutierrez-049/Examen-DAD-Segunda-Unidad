package pe.edu.upeu.lib.prestamos.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.lib.prestamos.dto.DevolucionDTO;
import pe.edu.upeu.lib.prestamos.entity.Devolucion;
import pe.edu.upeu.lib.prestamos.exception.RecursoNoEncontradoException;
import pe.edu.upeu.lib.prestamos.repository.DevolucionRepository;

import java.util.List;

@Service
public class DevolucionService {
    private final DevolucionRepository repository;

    public DevolucionService(DevolucionRepository repository) {
        this.repository = repository;
    }

    public List<DevolucionDTO> listar() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public DevolucionDTO obtener(Long id) {
        return toDto(buscar(id));
    }

    public DevolucionDTO crear(DevolucionDTO dto) {
        return toDto(repository.save(toEntity(dto, new Devolucion())));
    }

    public DevolucionDTO actualizar(Long id, DevolucionDTO dto) {
        return toDto(repository.save(toEntity(dto, buscar(id))));
    }

    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }

    private Devolucion buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Devolucion no encontrada: " + id));
    }

    private DevolucionDTO toDto(Devolucion devolucion) {
        return new DevolucionDTO(devolucion.getIdDevolucion(), devolucion.getIdPrestamo(), devolucion.getFechaDevolucion(), devolucion.getObservacion(), devolucion.getEstado());
    }

    private Devolucion toEntity(DevolucionDTO dto, Devolucion devolucion) {
        devolucion.setIdPrestamo(dto.idPrestamo());
        devolucion.setFechaDevolucion(dto.fechaDevolucion());
        devolucion.setObservacion(dto.observacion());
        devolucion.setEstado(dto.estado());
        return devolucion;
    }
}
