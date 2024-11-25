package com.balidea.gestion.libreria.app.mapper;

import com.balidea.gestion.libreria.app.model.dto.AutorDTO;
import com.balidea.gestion.libreria.app.model.entity.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "nacionalidad", target = "nacionalidad")
    @Mapping(source = "fecNacimiento", target = "fecNacimiento")
    Autor toEntity(AutorDTO autorDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "nacionalidad", target = "nacionalidad")
    @Mapping(source = "fecNacimiento", target = "fecNacimiento")
    AutorDTO toDto(Autor autor);

    List<Autor> toEntityList(List<AutorDTO> toDoDTOs);

    List<AutorDTO> toDtoList(List<Autor> toDos);
}
