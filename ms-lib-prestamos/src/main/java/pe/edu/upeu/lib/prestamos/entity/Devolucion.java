package pe.edu.upeu.lib.prestamos.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "devoluciones")
public class Devolucion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devolucion")
    private Long idDevolucion;
    @Column(name = "id_prestamo")
    private Long idPrestamo;
    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;
    private String observacion;
    private String estado;
    @PrePersist void prePersist() { if (fechaDevolucion == null) fechaDevolucion = LocalDate.now(); if (estado == null) estado = "REGISTRADA"; }
    public Long getIdDevolucion() { return idDevolucion; }
    public void setIdDevolucion(Long idDevolucion) { this.idDevolucion = idDevolucion; }
    public Long getIdPrestamo() { return idPrestamo; }
    public void setIdPrestamo(Long idPrestamo) { this.idPrestamo = idPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
