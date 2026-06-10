package pe.edu.upeu.lib.multas.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MultaDTO(Long idMulta, @NotNull Long idUsuario, Long idPrestamo, @DecimalMin("0.0") BigDecimal monto, String motivo, LocalDateTime fechaGeneracion, LocalDateTime fechaPago, String estado) {
}

