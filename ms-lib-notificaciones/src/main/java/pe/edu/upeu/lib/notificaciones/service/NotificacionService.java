package pe.edu.upeu.lib.notificaciones.service;

import pe.edu.upeu.lib.notificaciones.dto.NotificacionDTO;
import pe.edu.upeu.lib.notificaciones.entity.Notificacion;
import pe.edu.upeu.lib.notificaciones.exception.RecursoNoEncontradoException;
import pe.edu.upeu.lib.notificaciones.repository.NotificacionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificacionService {
    private final NotificacionRepository repository;
    public NotificacionService(NotificacionRepository repository) { this.repository = repository; }
    public List<NotificacionDTO> listar() { return repository.findAll().stream().map(this::toDto).toList(); }
    public NotificacionDTO obtener(Long id) { return toDto(buscar(id)); }
    public NotificacionDTO crear(NotificacionDTO dto) { return toDto(repository.save(toEntity(dto, new Notificacion()))); }
    public NotificacionDTO actualizar(Long id, NotificacionDTO dto) { return toDto(repository.save(toEntity(dto, buscar(id)))); }
    public void eliminar(Long id) { repository.delete(buscar(id)); }
    private Notificacion buscar(Long id) { return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Notificacion no encontrada: " + id)); }
    private NotificacionDTO toDto(Notificacion n) { return new NotificacionDTO(n.getIdNotificacion(), n.getIdUsuario(), n.getTitulo(), n.getMensaje(), n.getTipo(), n.getFechaEnvio(), n.getEstado()); }
    private Notificacion toEntity(NotificacionDTO dto, Notificacion n) { n.setIdUsuario(dto.idUsuario()); n.setTitulo(dto.titulo()); n.setMensaje(dto.mensaje()); n.setTipo(dto.tipo()); n.setFechaEnvio(dto.fechaEnvio()); n.setEstado(dto.estado()); return n; }
}
