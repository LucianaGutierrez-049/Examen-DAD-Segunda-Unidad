package pe.edu.upeu.lib.reservas.service;

import org.springframework.stereotype.Service;
import pe.edu.upeu.lib.reservas.dto.HistorialReservaDTO;
import pe.edu.upeu.lib.reservas.entity.HistorialReserva;
import pe.edu.upeu.lib.reservas.entity.Reserva;
import pe.edu.upeu.lib.reservas.exception.RecursoNoEncontradoException;
import pe.edu.upeu.lib.reservas.repository.HistorialReservaRepository;
import pe.edu.upeu.lib.reservas.repository.ReservaRepository;

import java.util.List;

@Service
public class HistorialReservaService {
    private final HistorialReservaRepository repository;
    private final ReservaRepository reservaRepository;

    public HistorialReservaService(HistorialReservaRepository repository, ReservaRepository reservaRepository) {
        this.repository = repository;
        this.reservaRepository = reservaRepository;
    }

    public List<HistorialReservaDTO> listar() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public HistorialReservaDTO obtener(Long id) {
        return toDto(buscar(id));
    }

    public HistorialReservaDTO crear(HistorialReservaDTO dto) {
        return toDto(repository.save(toEntity(dto, new HistorialReserva())));
    }

    public HistorialReservaDTO actualizar(Long id, HistorialReservaDTO dto) {
        return toDto(repository.save(toEntity(dto, buscar(id))));
    }

    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }

    private HistorialReserva buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Historial de reserva no encontrado: " + id));
    }

    private Reserva reserva(Long id) {
        return reservaRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Reserva no encontrada: " + id));
    }

    private HistorialReservaDTO toDto(HistorialReserva historial) {
        Long idReserva = historial.getReserva() == null ? null : historial.getReserva().getIdReserva();
        return new HistorialReservaDTO(historial.getIdHistorial(), idReserva, historial.getEstadoAnterior(), historial.getEstadoNuevo(), historial.getFechaCambio(), historial.getObservacion());
    }

    private HistorialReserva toEntity(HistorialReservaDTO dto, HistorialReserva historial) {
        historial.setReserva(reserva(dto.idReserva()));
        historial.setEstadoAnterior(dto.estadoAnterior());
        historial.setEstadoNuevo(dto.estadoNuevo());
        historial.setFechaCambio(dto.fechaCambio());
        historial.setObservacion(dto.observacion());
        return historial;
    }
}
