package com.balidea.gestion.libreria.app.service;

import com.balidea.gestion.libreria.app.model.dto.LibroDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface LibroService {

    LibroDTO guardarLibro(LibroDTO libroDTO);

    LibroDTO obtenerLibroPorId(Long id);

    List<LibroDTO> obtenerLibros();

    LibroDTO actualizarLibro(LibroDTO libroDTO);

    void eliminarLibro(Long id);

    /**
     * Paginación para listar todos los libros
     * */
    Page<LibroDTO> obtenerLibrosPaginado(Pageable pageable);

    /**
     * Operación para verificar la disponibilidad de un libro
     * */
    boolean esDisponiblePorIsbn(String isbn);

    Map<Integer, List<LibroDTO>> agruparLibrosPorAnoPublicacion();
}
