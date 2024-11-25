package com.balidea.gestion.libreria.app.model.dto;

import com.balidea.gestion.libreria.app.model.EstadoLibro;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Schema(description = "DTO que representa la entidad Libro")
public class LibroDTO {

    @Schema(description = "ID del libro", example = "1")
    private Long id;

    @Schema(description = "Título del libro", example = "La ciudad y los perros")
    private String titulo;

    @Schema(description = "ID del autor del libro", example = "1")
    private Long idAutor;

    @Schema(description = "Nombre del autor del libro", example = "Gabriel García Marquez")
    private String nombre;

    @Schema(description = "ISBN del libro", example = "978-8-43-763897-3")
    private String isbn;

    @Schema(description = "Fecha de publicación del libro", example = "1963-11-24")
    private LocalDate fecPublicacion;

    @Schema(description = "Estado del libro", example = "DISPONIBLE")
    @Enumerated(EnumType.STRING)
    private EstadoLibro estado;

    @Schema(description = "Obtiene el año de publicación del libro", example = "2021")
    public int getAnoPublicacion() {
        return fecPublicacion != null ? fecPublicacion.getYear() : 0;
    }

}
