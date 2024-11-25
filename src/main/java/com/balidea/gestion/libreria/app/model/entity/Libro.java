package com.balidea.gestion.libreria.app.model.entity;

import com.balidea.gestion.libreria.app.model.EstadoLibro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "fec_publicacion")
    private LocalDate fecPublicacion;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoLibro estado;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false, foreignKey = @ForeignKey(name = "fk_libro_autor"))
    private Autor autor;

    @OneToMany(mappedBy = "libro")
    private Set<Prestamo> prestamos;
}
