package com.balidea.gestion.libreria.app.service.impl;

import com.balidea.gestion.libreria.app.exception.EntityNotFoundException;
import com.balidea.gestion.libreria.app.mapper.LibroMapper;
import com.balidea.gestion.libreria.app.model.EstadoLibro;
import com.balidea.gestion.libreria.app.model.dto.LibroDTO;
import com.balidea.gestion.libreria.app.model.entity.Libro;
import com.balidea.gestion.libreria.app.repository.LibroRepository;
import com.balidea.gestion.libreria.app.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para la gestión de libros.
 * Proporciona operaciones CRUD y funcionalidades adicionales relacionadas con los libros.
 */
@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroMapper libroMapper;

    /**
     * Guarda un libro en el sistema.
     * Al guardar, el estado del libro se establece como DISPONIBLE por defecto.
     *
     * @param libroDTO Objeto DTO del libro a guardar.
     * @return LibroDTO con los datos del libro guardado.
     */
    @Override
    public LibroDTO guardarLibro(LibroDTO libroDTO) {
        libroDTO.setEstado(EstadoLibro.DISPONIBLE);
        Libro libro = libroMapper.toEntity(libroDTO);
        Libro libroGuardado = libroRepository.save(libro);
        return libroMapper.toDto(libroGuardado);
    }

    /**
     * Obtiene un libro por su ID.
     *
     * @param id ID del libro a buscar.
     * @return LibroDTO con los datos del libro encontrado.
     * @throws EntityNotFoundException Si no se encuentra un libro con el ID proporcionado.
     */
    @Override
    public LibroDTO obtenerLibroPorId(Long id) {
        Optional<Libro> libro = libroRepository.findById(id);
        return libro.map(libroMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Libro", id));
    }

    /**
     * Obtiene una lista de todos los libros.
     *
     * @return Lista de LibroDTO con todos los libros registrados.
     */
    @Override
    public List<LibroDTO> obtenerLibros() {
        List<Libro> libros = libroRepository.findAll();
        return libroMapper.toDtoList(libros);
    }

    /**
     * Actualiza los datos de un libro existente.
     *
     * @param libroDTO Objeto DTO con los datos actualizados del libro.
     * @return LibroDTO con los datos del libro actualizado.
     * @throws EntityNotFoundException Si el libro no existe en el sistema.
     */
    @Override
    public LibroDTO actualizarLibro(LibroDTO libroDTO) {
        if (libroRepository.existsById(libroDTO.getId())) {
            Libro libro = libroMapper.toEntity(libroDTO);
            Libro libroActualizado = libroRepository.save(libro);
            return libroMapper.toDto(libroActualizado);
        } else {
            throw new EntityNotFoundException("Libro", libroDTO.getId());
        }
    }

    /**
     * Elimina un libro por su ID.
     *
     * @param id ID del libro a eliminar.
     * @throws EntityNotFoundException Si el libro no existe en el sistema.
     */
    @Override
    public void eliminarLibro(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Libro", id);
        }
    }

    /**
     * Obtiene una página de libros según los parámetros de paginación.
     *
     * @param pageable Objeto Pageable con los parámetros de paginación.
     * @return Página de LibroDTO con los libros.
     */
    @Override
    public Page<LibroDTO> obtenerLibrosPaginado(Pageable pageable) {
        Page<Libro> librosPage = libroRepository.findAll(pageable);
        return librosPage.map(libroMapper::toDto);
    }

    /**
     * Verifica si un libro está disponible por su ISBN.
     *
     * @param isbn ISBN del libro a verificar.
     * @return true si el libro está disponible, false en caso contrario.
     */
    @Override
    public boolean esDisponiblePorIsbn(String isbn) {
        Optional<Libro> libro = libroRepository.findByIsbn(isbn);
        return libro.map(l -> l.getEstado() == EstadoLibro.DISPONIBLE).orElse(false);
    }

    /**
     * Agrupa los libros por año de publicación.
     *
     * @return Mapa donde la clave es el año de publicación y el valor es la lista de libros publicados en ese año.
     */
    @Override
    public Map<Integer, List<LibroDTO>> agruparLibrosPorAnoPublicacion() {
        List<LibroDTO> libros = obtenerLibros();
        return libros.stream()
                .collect(Collectors.groupingBy(LibroDTO::getAnoPublicacion));
    }
}
