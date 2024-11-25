package com.balidea.gestion.libreria.app.mapper;

import com.balidea.gestion.libreria.app.model.dto.PrestamoDTO;
import com.balidea.gestion.libreria.app.model.entity.Prestamo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrestamoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "idLibro", target = "libro.id")
    @Mapping(source = "nomLibro", target = "libro.titulo")
    @Mapping(source = "isbn", target = "libro.isbn")
    @Mapping(source = "fecDevolucion", target = "fecDevolucion")
    @Mapping(source = "fecPrestamo", target = "fecPrestamo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "lector", target = "lector")
    Prestamo toEntity(PrestamoDTO prestamoDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "libro.id", target = "idLibro")
    @Mapping(source = "fecDevolucion", target = "fecDevolucion")
    @Mapping(source = "fecPrestamo", target = "fecPrestamo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "lector", target = "lector")
    @Mapping(source = "libro.titulo", target = "nomLibro")
    @Mapping(source = "libro.isbn", target = "isbn")
    PrestamoDTO toDto(Prestamo prestamo);

    List<Prestamo> toEntityList(List<PrestamoDTO> toDoDTOs);

    List<PrestamoDTO> toDtoList(List<Prestamo> toDos);
}
