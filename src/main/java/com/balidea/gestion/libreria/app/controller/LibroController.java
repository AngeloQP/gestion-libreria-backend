package com.balidea.gestion.libreria.app.controller;

import com.balidea.gestion.libreria.app.model.dto.LibroDTO;
import com.balidea.gestion.libreria.app.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("libro")
@Tag(name = "Libro", description = "Gestión de Libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Operation(summary = "Crear un nuevo libro", description = "Este método permite crear un nuevo libro en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Libro creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<LibroDTO> guardarLibro(@RequestBody LibroDTO libroDTO) {
        LibroDTO libroCreado = libroService.guardarLibro(libroDTO);
        return new ResponseEntity<>(libroCreado, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todos los libros", description = "Este método permite obtener una lista de todos los libros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de libros obtenida correctamente"),
    })
    @GetMapping
    public ResponseEntity<List<LibroDTO>> obtenerLibros() {
        List<LibroDTO> libros = libroService.obtenerLibros();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un libro por su ID", description = "Este método permite obtener los detalles de un libro según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro encontrado"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> obtenerLibroPorId(@PathVariable Long id) {
        LibroDTO libroDTO = libroService.obtenerLibroPorId(id);
        return new ResponseEntity<>(libroDTO, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar un libro existente", description = "Este método permite actualizar los datos de un libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @PutMapping
    public ResponseEntity<LibroDTO> actualizarLibro(@RequestBody LibroDTO libroDTO) {
        LibroDTO libroActualizado = libroService.actualizarLibro(libroDTO);
        return new ResponseEntity<>(libroActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un libro", description = "Este método permite eliminar un libro por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Libro eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Comprobar disponibilidad de libro por ISBN", description = "Este método permite verificar si un libro está disponible según su ISBN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de disponibilidad obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "ISBN no encontrado")
    })
    @GetMapping("/disponibilidad/{isbn}")
    public ResponseEntity<Boolean> esDisponiblePorIsbn(@PathVariable String isbn) {
        boolean disponible = libroService.esDisponiblePorIsbn(isbn);
        return new ResponseEntity<>(disponible, HttpStatus.OK);
    }

    @Operation(summary = "Obtener libros con paginación", description = "Este método permite obtener libros de manera paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libros obtenidos correctamente"),
    })
    @GetMapping("/paginado")
    public ResponseEntity<Page<LibroDTO>> obtenerLibrosPaginado(Pageable pageable) {
        Page<LibroDTO> librosPage = libroService.obtenerLibrosPaginado(pageable);
        return new ResponseEntity<>(librosPage, HttpStatus.OK);
    }

    @Operation(summary = "Agrupar libros por año de publicación", description = "Este método permite agrupar a los libros por año de publicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libros agrupados correctamente"),
    })
    @GetMapping("/agrupar/ano-publicacion")
    public ResponseEntity<Map<Integer, List<LibroDTO>>> agruparLibrosPorAnoPublicacion() {
        Map<Integer, List<LibroDTO>> librosAgrupados = libroService.agruparLibrosPorAnoPublicacion();
        return new ResponseEntity<>(librosAgrupados, HttpStatus.OK);
    }
}
