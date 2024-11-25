package com.balidea.gestion.libreria.app.service;

import com.balidea.gestion.libreria.app.model.dto.PrestamoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrestamoService {

    PrestamoDTO guardarPrestamo(PrestamoDTO prestamoDTO);

    PrestamoDTO obtenerPrestamoPorId(Long id);

    List<PrestamoDTO> obtenerPrestamos();

    PrestamoDTO actualizarPrestamo(PrestamoDTO prestamoDTO);

    void eliminarPrestamo(Long id);

    /**
     * Operación para listar todos los préstamos de un libro específico
     * */
    List<PrestamoDTO> obtenerPrestamoPorLibro(Long idLibro);

    /**
     * Paginación para listar todos los prestamos
     */
    Page<PrestamoDTO> obtenerPrestamosPaginado(Pageable pageable);


}
