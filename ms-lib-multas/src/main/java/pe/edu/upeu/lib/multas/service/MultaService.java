package pe.edu.upeu.lib.multas.service;

import pe.edu.upeu.lib.multas.client.NotificacionClient;
import pe.edu.upeu.lib.multas.dto.MultaDTO;
import pe.edu.upeu.lib.multas.dto.NotificacionRequest;
import pe.edu.upeu.lib.multas.entity.Multa;
import pe.edu.upeu.lib.multas.exception.RecursoNoEncontradoException;
import pe.edu.upeu.lib.multas.repository.MultaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MultaService {
    private final MultaRepository repository; private final NotificacionClient notificacionClient;
    public MultaService(MultaRepository repository, NotificacionClient notificacionClient) { this.repository = repository; this.notificacionClient = notificacionClient; }
    public List<MultaDTO> listar() { return repository.findAll().stream().map(this::toDto).toList(); }
    public MultaDTO obtener(Long id) { return toDto(buscar(id)); }
    public MultaDTO crear(MultaDTO dto) { Multa g = repository.save(toEntity(dto, new Multa())); notificacionClient.crear(new NotificacionRequest(g.getIdUsuario(), "Multa generada", g.getMotivo(), "MULTA")); return toDto(g); }
    public MultaDTO actualizar(Long id, MultaDTO dto) { return toDto(repository.save(toEntity(dto, buscar(id)))); }
    public MultaDTO pagar(Long id) { Multa m = buscar(id); m.setEstado("PAGADA"); m.setFechaPago(LocalDateTime.now()); return toDto(repository.save(m)); }
    public void eliminar(Long id) { repository.delete(buscar(id)); }
    private Multa buscar(Long id) { return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Multa no encontrada: " + id)); }
    private MultaDTO toDto(Multa m) { return new MultaDTO(m.getIdMulta(), m.getIdUsuario(), m.getIdPrestamo(), m.getMonto(), m.getMotivo(), m.getFechaGeneracion(), m.getFechaPago(), m.getEstado()); }
    private Multa toEntity(MultaDTO dto, Multa m) { m.setIdUsuario(dto.idUsuario()); m.setIdPrestamo(dto.idPrestamo()); m.setMonto(dto.monto()); m.setMotivo(dto.motivo()); m.setFechaGeneracion(dto.fechaGeneracion()); m.setFechaPago(dto.fechaPago()); m.setEstado(dto.estado()); return m; }
}
