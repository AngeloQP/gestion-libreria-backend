package com.balidea.gestion.libreria.app.model.dto;

import com.balidea.gestion.libreria.app.model.EstadoPrestamo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Schema(description = "DTO que representa la entidad Prestamo")
public class PrestamoDTO {

    @Schema(description = "ID del préstamo", example = "1")
    private Long id;

    @Schema(description = "ID del libro prestado", example = "1")
    private Long idLibro;

    @Schema(description = "Nombre del libro prestado", example = "1")
    private String nomLibro;

    @Schema(description = "ISBN del libro prestado", example = "1")
    private String isbn;

    @Schema(description = "Fecha de devolución del préstamo", example = "2024-11-24")
    private LocalDate fecDevolucion;

    @Schema(description = "Fecha del préstamo", example = "2024-11-24")
    private LocalDate fecPrestamo;

    @Schema(description = "Estado del préstamo", example = "FINALIZADO")
    private EstadoPrestamo estado;

    @Schema(description = "Lector", example = "2024-11-24")
    private String lector;

}
