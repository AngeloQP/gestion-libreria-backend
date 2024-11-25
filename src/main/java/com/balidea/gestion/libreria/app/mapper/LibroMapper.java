package com.balidea.gestion.libreria.app.mapper;

import com.balidea.gestion.libreria.app.model.dto.LibroDTO;
import com.balidea.gestion.libreria.app.model.entity.Libro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibroMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "titulo", target = "titulo")
    @Mapping(source = "idAutor", target = "autor.id")
    @Mapping(source = "nombre", target = "autor.nombre")
    @Mapping(source = "isbn", target = "isbn")
    @Mapping(source = "fecPublicacion", target = "fecPublicacion")
    @Mapping(source = "estado", target = "estado")
    Libro toEntity(LibroDTO libroDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "titulo", target = "titulo")
    @Mapping(source = "autor.id", target = "idAutor")
    @Mapping(source = "autor.nombre", target = "nombre")
    @Mapping(source = "isbn", target = "isbn")
    @Mapping(source = "fecPublicacion", target = "fecPublicacion")
    @Mapping(source = "estado", target = "estado")
    LibroDTO toDto(Libro libro);

    List<Libro> toEntityList(List<LibroDTO> lstLibroDTO);

    List<LibroDTO> toDtoList(List<Libro> lstLibro);
}