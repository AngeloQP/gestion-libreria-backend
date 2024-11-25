package com.balidea.gestion.libreria.app.controller;

import com.balidea.gestion.libreria.app.model.dto.AutorDTO;
import com.balidea.gestion.libreria.app.service.AutorService;
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
@RequestMapping("autor")
@Tag(name = "Autor", description = "Gestión de Autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Operation(summary = "Crear un nuevo autor", description = "Este método permite crear un nuevo autor en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autor creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<AutorDTO> guardarAutor(@RequestBody AutorDTO autorDTO) {
        AutorDTO autorCreado = autorService.guardarAutor(autorDTO);
        return new ResponseEntity<>(autorCreado, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todos los autores", description = "Este método permite obtener una lista de todos los autores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de autores obtenida correctamente"),
    })
    @GetMapping
    public ResponseEntity<List<AutorDTO>> obtenerAutores() {
        List<AutorDTO> autores = autorService.obtenerAutores();
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un autor por su ID", description = "Este método permite obtener los detalles de un autor según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obtenerAutorPorId(@PathVariable Long id) {
        AutorDTO autorDTO = autorService.obtenerAutorPorId(id);
        return new ResponseEntity<>(autorDTO, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar un autor existente", description = "Este método permite actualizar los datos de un autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado")
    })
    @PutMapping
    public ResponseEntity<AutorDTO> actualizarAutor(@RequestBody AutorDTO autorDTO) {
        AutorDTO autorActualizado = autorService.actualizarAutor(autorDTO);
        return new ResponseEntity<>(autorActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un autor", description = "Este método permite eliminar un autor por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAutor(@PathVariable Long id) {
        autorService.eliminarAutor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Obtener autor con paginación", description = "Este método permite obtener autores de manera paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autores obtenidos correctamente"),
    })
    @GetMapping("/paginado")
    public ResponseEntity<Page<AutorDTO>> obtenerAutoresPaginado(Pageable pageable) {
        Page<AutorDTO> autoresPage = autorService.obtenerAutoresPaginado(pageable);
        return new ResponseEntity<>(autoresPage, HttpStatus.OK);
    }

    @Operation(summary = "Agrupar autores por nacionalidad", description = "Este método permite agrupar a los autores por su nacionalidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autores agrupados correctamente"),
    })
    @GetMapping("/agrupar/nacionalidad")
    public ResponseEntity<Map<String, List<AutorDTO>>> agruparAutoresPorNacionalidad() {
        Map<String, List<AutorDTO>> autoresAgrupados = autorService.agruparAutoresPorNacionalidad();
        return new ResponseEntity<>(autoresAgrupados, HttpStatus.OK);
    }
}
