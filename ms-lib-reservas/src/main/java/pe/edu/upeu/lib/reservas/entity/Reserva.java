package pe.edu.upeu.lib.reservas.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "id_libro")
    private Long idLibro;
    @Column(name = "fecha_reserva")
    private LocalDateTime fechaReserva;
    @Column(name = "fecha_expiracion")
    private LocalDateTime fechaExpiracion;
    private String estado;
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistorialReserva> historial = new ArrayList<>();
    @PrePersist void prePersist() { if (fechaReserva == null) fechaReserva = LocalDateTime.now(); if (fechaExpiracion == null) fechaExpiracion = fechaReserva.plusDays(2); if (estado == null) estado = "ACTIVA"; }
    public Long getIdReserva() { return idReserva; }
    public void setIdReserva(Long idReserva) { this.idReserva = idReserva; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public Long getIdLibro() { return idLibro; }
    public void setIdLibro(Long idLibro) { this.idLibro = idLibro; }
    public LocalDateTime getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDateTime fechaReserva) { this.fechaReserva = fechaReserva; }
    public LocalDateTime getFechaExpiracion() { return fechaExpiracion; }
    public void setFechaExpiracion(LocalDateTime fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public List<HistorialReserva> getHistorial() { return historial; }
    public void setHistorial(List<HistorialReserva> historial) { this.historial = historial; }
}
