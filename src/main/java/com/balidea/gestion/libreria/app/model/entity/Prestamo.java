package com.balidea.gestion.libreria.app.model.entity;


import com.balidea.gestion.libreria.app.model.EstadoPrestamo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fec_prestamo", nullable = false)
    private LocalDate fecPrestamo;

    @Column(name = "fec_devolucion")
    private LocalDate fecDevolucion;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    @Column(name = "lector", nullable = false)
    private String lector;

    @ManyToOne(optional = false)
    @JoinColumn(name = "libro_id", nullable = false, foreignKey = @ForeignKey(name = "fk_prestamo_libro"))
    private Libro libro;

}