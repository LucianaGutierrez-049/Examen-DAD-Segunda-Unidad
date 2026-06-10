package pe.edu.upeu.lib.prestamos.dto;

import java.math.BigDecimal;

public record MultaRequest(Long idUsuario, Long idPrestamo, BigDecimal monto, String motivo) {
}

