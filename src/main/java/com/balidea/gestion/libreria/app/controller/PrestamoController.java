package com.balidea.gestion.libreria.app.controller;

import com.balidea.gestion.libreria.app.model.dto.PrestamoDTO;
import com.balidea.gestion.libreria.app.service.PrestamoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("prestamo")
@Tag(name = "Prestamo", description = "Gestión de Préstamos de Libros")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @Operation(summary = "Crear un nuevo préstamo", description = "Este método permite crear un nuevo préstamo de libro en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Préstamo creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<PrestamoDTO> guardarPrestamo(@RequestBody PrestamoDTO prestamoDTO) {
        PrestamoDTO prestamoCreado = prestamoService.guardarPrestamo(prestamoDTO);
        return new ResponseEntity<>(prestamoCreado, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todos los préstamos", description = "Este método permite obtener una lista de todos los préstamos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de préstamos obtenida correctamente"),
    })
    @GetMapping
    public ResponseEntity<List<PrestamoDTO>> obtenerPrestamos() {
        List<PrestamoDTO> prestamos = prestamoService.obtenerPrestamos();
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un préstamo por su ID", description = "Este método permite obtener los detalles de un préstamo según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Préstamo encontrado"),
            @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDTO> obtenerPrestamoPorId(@PathVariable Long id) {
        PrestamoDTO prestamoDTO = prestamoService.obtenerPrestamoPorId(id);
        return new ResponseEntity<>(prestamoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar un préstamo existente", description = "Este método permite actualizar los datos de un préstamo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Préstamo actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    })
    @PutMapping
    public ResponseEntity<PrestamoDTO> actualizarPrestamo(@RequestBody PrestamoDTO prestamoDTO) {
        PrestamoDTO prestamoActualizado = prestamoService.actualizarPrestamo(prestamoDTO);
        return new ResponseEntity<>(prestamoActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un préstamo", description = "Este método permite eliminar un préstamo por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Préstamo eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Long id) {
        prestamoService.eliminarPrestamo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Obtener préstamos por libro", description = "Este método permite obtener todos los préstamos asociados a un libro específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Préstamos obtenidos correctamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @GetMapping("/libro/{idLibro}")
    public ResponseEntity<List<PrestamoDTO>> obtenerPrestamosPorLibro(@PathVariable Long idLibro) {
        List<PrestamoDTO> prestamos = prestamoService.obtenerPrestamoPorLibro(idLibro);
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }

    @Operation(summary = "Obtener préstamos con paginación", description = "Este método permite obtener préstamos de manera paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Préstamos obtenidos correctamente"),
    })
    @GetMapping("/paginado")
    public ResponseEntity<Page<PrestamoDTO>> obtenerPrestamosPaginados(Pageable pageable) {
        Page<PrestamoDTO> prestamosPage = prestamoService.obtenerPrestamosPaginado(pageable);
        return new ResponseEntity<>(prestamosPage, HttpStatus.OK);
    }
}
