package pe.edu.upeu.lib.multas.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "multas")
public class Multa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multa")
    private Long idMulta;
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "id_prestamo")
    private Long idPrestamo;
    private BigDecimal monto;
    private String motivo;
    @Column(name = "fecha_generacion")
    private LocalDateTime fechaGeneracion;
    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;
    private String estado;
    @PrePersist void prePersist() { if (fechaGeneracion == null) fechaGeneracion = LocalDateTime.now(); if (estado == null) estado = "PENDIENTE"; }
    public Long getIdMulta() { return idMulta; }
    public void setIdMulta(Long idMulta) { this.idMulta = idMulta; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdPrestamo() { return idPrestamo; }
    public void setIdPrestamo(Long idPrestamo) { this.idPrestamo = idPrestamo; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }
    public LocalDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
