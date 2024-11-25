package com.balidea.gestion.libreria.app.service;

import com.balidea.gestion.libreria.app.model.dto.AutorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface AutorService {

    AutorDTO guardarAutor(AutorDTO autorDTO);

    AutorDTO obtenerAutorPorId(Long id);

    List<AutorDTO> obtenerAutores();

    AutorDTO actualizarAutor(AutorDTO autorDTO);

    void eliminarAutor(Long id);

    /**
     * Paginación para listar todos los autores
     * */
    Page<AutorDTO> obtenerAutoresPaginado(Pageable pageable);

    Map<String, List<AutorDTO>> agruparAutoresPorNacionalidad();

}
