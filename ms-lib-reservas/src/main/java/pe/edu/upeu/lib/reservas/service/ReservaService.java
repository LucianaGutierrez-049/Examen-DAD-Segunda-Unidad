package pe.edu.upeu.lib.reservas.service;

import pe.edu.upeu.lib.reservas.client.LibroClient;
import pe.edu.upeu.lib.reservas.client.NotificacionClient;
import pe.edu.upeu.lib.reservas.client.UsuarioClient;
import pe.edu.upeu.lib.reservas.dto.NotificacionRequest;
import pe.edu.upeu.lib.reservas.dto.ReservaDTO;
import pe.edu.upeu.lib.reservas.entity.HistorialReserva;
import pe.edu.upeu.lib.reservas.entity.Reserva;
import pe.edu.upeu.lib.reservas.exception.RecursoNoEncontradoException;
import pe.edu.upeu.lib.reservas.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository repository; private final UsuarioClient usuarioClient; private final LibroClient libroClient; private final NotificacionClient notificacionClient;
    public ReservaService(ReservaRepository repository, UsuarioClient usuarioClient, LibroClient libroClient, NotificacionClient notificacionClient) { this.repository = repository; this.usuarioClient = usuarioClient; this.libroClient = libroClient; this.notificacionClient = notificacionClient; }
    public List<ReservaDTO> listar() { return repository.findAll().stream().map(this::toDto).toList(); }
    public ReservaDTO obtener(Long id) { return toDto(buscar(id)); }
    public ReservaDTO crear(ReservaDTO dto) { usuarioClient.obtener(dto.idUsuario()); libroClient.obtener(dto.idLibro()); Reserva r = toEntity(dto, new Reserva()); Reserva g = repository.save(r); notificacionClient.crear(new NotificacionRequest(g.getIdUsuario(), "Reserva registrada", "Se registro la reserva " + g.getIdReserva(), "RESERVA")); return toDto(g); }
    public ReservaDTO actualizar(Long id, ReservaDTO dto) { Reserva r = buscar(id); String anterior = r.getEstado(); Reserva g = repository.save(toEntity(dto, r)); HistorialReserva h = new HistorialReserva(); h.setReserva(g); h.setEstadoAnterior(anterior); h.setEstadoNuevo(g.getEstado()); h.setObservacion("Actualizacion de reserva"); g.getHistorial().add(h); notificacionClient.crear(new NotificacionRequest(g.getIdUsuario(), "Reserva actualizada", "La reserva " + g.getIdReserva() + " cambio a " + g.getEstado(), "RESERVA")); return toDto(repository.save(g)); }
    public void eliminar(Long id) { repository.delete(buscar(id)); }
    private Reserva buscar(Long id) { return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Reserva no encontrada: " + id)); }
    private ReservaDTO toDto(Reserva r) { return new ReservaDTO(r.getIdReserva(), r.getIdUsuario(), r.getIdLibro(), r.getFechaReserva(), r.getFechaExpiracion(), r.getEstado()); }
    private Reserva toEntity(ReservaDTO dto, Reserva r) { r.setIdUsuario(dto.idUsuario()); r.setIdLibro(dto.idLibro()); r.setFechaReserva(dto.fechaReserva()); r.setFechaExpiracion(dto.fechaExpiracion()); r.setEstado(dto.estado()); return r; }
}
