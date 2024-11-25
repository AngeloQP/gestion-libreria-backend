package com.balidea.gestion.libreria.app.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Schema(description = "DTO que representa la entidad Autor")
public class AutorDTO {
    @Schema(description = "ID del autor", example = "1")
    private Long id;

    @Schema(description = "Nombre del autor", example = "Mario Vargas Llosa")
    private String nombre;

    @Schema(description = "Nacionalidad del autor", example = "Peruana")
    private String nacionalidad;

    @Schema(description = "Fecha de nacimiento del autor", example = "1936-03-28")
    private LocalDate fecNacimiento;
}
