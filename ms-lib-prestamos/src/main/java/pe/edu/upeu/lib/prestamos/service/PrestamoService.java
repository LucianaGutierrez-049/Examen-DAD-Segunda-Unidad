package pe.edu.upeu.lib.prestamos.service;

import pe.edu.upeu.lib.prestamos.client.LibroClient;
import pe.edu.upeu.lib.prestamos.client.MultaClient;
import pe.edu.upeu.lib.prestamos.client.NotificacionClient;
import pe.edu.upeu.lib.prestamos.client.UsuarioClient;
import pe.edu.upeu.lib.prestamos.dto.*;
import pe.edu.upeu.lib.prestamos.entity.DetallePrestamo;
import pe.edu.upeu.lib.prestamos.entity.Devolucion;
import pe.edu.upeu.lib.prestamos.entity.Prestamo;
import pe.edu.upeu.lib.prestamos.exception.RecursoNoEncontradoException;
import pe.edu.upeu.lib.prestamos.repository.DevolucionRepository;
import pe.edu.upeu.lib.prestamos.repository.PrestamoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PrestamoService {
    private final PrestamoRepository repository;
    private final DevolucionRepository devolucionRepository;
    private final UsuarioClient usuarioClient;
    private final LibroClient libroClient;
    private final MultaClient multaClient;
    private final NotificacionClient notificacionClient;
    public PrestamoService(PrestamoRepository repository, DevolucionRepository devolucionRepository, UsuarioClient usuarioClient, LibroClient libroClient, MultaClient multaClient, NotificacionClient notificacionClient) { this.repository = repository; this.devolucionRepository = devolucionRepository; this.usuarioClient = usuarioClient; this.libroClient = libroClient; this.multaClient = multaClient; this.notificacionClient = notificacionClient; }
    public List<PrestamoDTO> listar() { return repository.findAll().stream().map(this::toDto).toList(); }
    public PrestamoDTO obtener(Long id) { return toDto(buscar(id)); }
    @Transactional
    public PrestamoDTO crear(PrestamoDTO dto) {
        usuarioClient.obtener(dto.idUsuario());
        Prestamo prestamo = new Prestamo();
        prestamo.setIdUsuario(dto.idUsuario());
        prestamo.setFechaPrestamo(dto.fechaPrestamo() == null ? LocalDate.now() : dto.fechaPrestamo());
        prestamo.setFechaVencimiento(dto.fechaVencimiento() == null ? prestamo.getFechaPrestamo().plusDays(7) : dto.fechaVencimiento());
        prestamo.setEstado(dto.estado() == null ? "ACTIVO" : dto.estado());
        for (DetallePrestamoDTO detalleDto : dto.detalles()) {
            libroClient.obtener(detalleDto.idLibro());
            libroClient.descontar(detalleDto.idLibro(), new StockRequest(detalleDto.cantidad()));
            DetallePrestamo detalle = new DetallePrestamo();
            detalle.setPrestamo(prestamo);
            detalle.setIdLibro(detalleDto.idLibro());
            detalle.setCantidad(detalleDto.cantidad());
            prestamo.getDetalles().add(detalle);
        }
        Prestamo guardado = repository.save(prestamo);
        notificacionClient.crear(new NotificacionRequest(guardado.getIdUsuario(), "Prestamo registrado", "Se registro el prestamo " + guardado.getIdPrestamo(), "PRESTAMO"));
        return toDto(guardado);
    }
    public PrestamoDTO actualizar(Long id, PrestamoDTO dto) { Prestamo p = buscar(id); p.setFechaVencimiento(dto.fechaVencimiento()); p.setEstado(dto.estado()); return toDto(repository.save(p)); }
    public void eliminar(Long id) { repository.delete(buscar(id)); }
    @Transactional
    public DevolucionDTO devolver(DevolucionDTO dto) {
        Prestamo prestamo = buscar(dto.idPrestamo());
        LocalDate fecha = dto.fechaDevolucion() == null ? LocalDate.now() : dto.fechaDevolucion();
        prestamo.setFechaDevolucion(fecha);
        prestamo.setEstado("DEVUELTO");
        for (DetallePrestamo detalle : prestamo.getDetalles()) {
            libroClient.aumentar(detalle.getIdLibro(), new StockRequest(detalle.getCantidad()));
        }
        if (prestamo.getFechaVencimiento() != null && fecha.isAfter(prestamo.getFechaVencimiento())) {
            long dias = ChronoUnit.DAYS.between(prestamo.getFechaVencimiento(), fecha);
            multaClient.crear(new MultaRequest(prestamo.getIdUsuario(), prestamo.getIdPrestamo(), BigDecimal.valueOf(dias * 2.0), "Retraso de " + dias + " dias"));
        }
        Devolucion devolucion = new Devolucion();
        devolucion.setIdPrestamo(prestamo.getIdPrestamo());
        devolucion.setFechaDevolucion(fecha);
        devolucion.setObservacion(dto.observacion());
        devolucion.setEstado(dto.estado() == null ? "REGISTRADA" : dto.estado());
        Devolucion guardada = devolucionRepository.save(devolucion);
        notificacionClient.crear(new NotificacionRequest(prestamo.getIdUsuario(), "Devolucion registrada", "Se registro la devolucion del prestamo " + prestamo.getIdPrestamo(), "PRESTAMO"));
        return new DevolucionDTO(guardada.getIdDevolucion(), guardada.getIdPrestamo(), guardada.getFechaDevolucion(), guardada.getObservacion(), guardada.getEstado());
    }
    private Prestamo buscar(Long id) { return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Prestamo no encontrado: " + id)); }
    private PrestamoDTO toDto(Prestamo p) { return new PrestamoDTO(p.getIdPrestamo(), p.getIdUsuario(), p.getFechaPrestamo(), p.getFechaVencimiento(), p.getFechaDevolucion(), p.getEstado(), p.getDetalles().stream().map(d -> new DetallePrestamoDTO(d.getIdDetalle(), p.getIdPrestamo(), d.getIdLibro(), d.getCantidad())).toList()); }
}
