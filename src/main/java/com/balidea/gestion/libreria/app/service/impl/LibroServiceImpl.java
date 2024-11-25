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

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroMapper libroMapper;

    @Override
    public LibroDTO guardarLibro(LibroDTO libroDTO) {
        libroDTO.setEstado(EstadoLibro.DISPONIBLE);

        Libro libro = libroMapper.toEntity(libroDTO);
        Libro libroGuardado = libroRepository.save(libro);

        return libroMapper.toDto(libroGuardado);
    }

    @Override
    public LibroDTO obtenerLibroPorId(Long id) {
        Optional<Libro> libro = libroRepository.findById(id);

        return libro.map(libroMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Libro", id));
    }

    @Override
    public List<LibroDTO> obtenerLibros() {
        List<Libro> libros = libroRepository.findAll();

        return libroMapper.toDtoList(libros);
    }

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

    @Override
    public void eliminarLibro(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Libro", id);
        }
    }

    @Override
    public Page<LibroDTO> obtenerLibrosPaginado(Pageable pageable) {
        Page<Libro> librosPage = libroRepository.findAll(pageable);

        return librosPage.map(libroMapper::toDto);
    }

    @Override
    public boolean esDisponiblePorIsbn(String isbn) {
        Optional<Libro> libro = libroRepository.findByIsbn(isbn);

        return libro.map(l -> l.getEstado() == EstadoLibro.DISPONIBLE).orElse(false);
    }

    @Override
    public Map<Integer, List<LibroDTO>> agruparLibrosPorAnoPublicacion() {
        List<LibroDTO> libros = obtenerLibros();

        return libros.stream()
                .collect(Collectors.groupingBy(LibroDTO::getAnoPublicacion));
    }
}
