package pe.edu.upeu.lib.reservas.dto;

import java.time.LocalDateTime;

public record HistorialReservaDTO(Long idHistorial, Long idReserva, String estadoAnterior, String estadoNuevo, LocalDateTime fechaCambio, String observacion) {
}

