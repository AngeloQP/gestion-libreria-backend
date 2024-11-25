package com.balidea.gestion.libreria.app.service.impl;

import com.balidea.gestion.libreria.app.exception.EntityNotFoundException;
import com.balidea.gestion.libreria.app.mapper.AutorMapper;
import com.balidea.gestion.libreria.app.model.dto.AutorDTO;
import com.balidea.gestion.libreria.app.model.entity.Autor;
import com.balidea.gestion.libreria.app.repository.AutorRepository;
import com.balidea.gestion.libreria.app.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de gestión de autores.
 * Proporciona operaciones CRUD y funcionalidades adicionales relacionadas con autores.
 */
@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private AutorMapper autorMapper;

    /**
     * Guarda un autor en el sistema.
     *
     * @param autorDTO Objeto DTO del autor a guardar.
     * @return AutorDTO con los datos del autor guardado.
     */
    @Override
    public AutorDTO guardarAutor(AutorDTO autorDTO) {
        Autor autor = autorMapper.toEntity(autorDTO);
        Autor autorGuardado = autorRepository.save(autor);
        return autorMapper.toDto(autorGuardado);
    }

    /**
     * Obtiene un autor por su ID.
     *
     * @param id ID del autor a buscar.
     * @return AutorDTO con los datos del autor encontrado.
     * @throws EntityNotFoundException Si no se encuentra el autor con el ID proporcionado.
     */
    @Override
    public AutorDTO obtenerAutorPorId(Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        return autor.map(autorMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Autor", id));
    }

    /**
     * Obtiene una lista de todos los autores.
     *
     * @return Lista de AutorDTO con todos los autores.
     */
    @Override
    public List<AutorDTO> obtenerAutores() {
        List<Autor> autores = autorRepository.findAll();
        return autorMapper.toDtoList(autores);
    }

    /**
     * Actualiza los datos de un autor existente.
     *
     * @param autorDTO Objeto DTO con los datos actualizados del autor.
     * @return AutorDTO con los datos del autor actualizado.
     * @throws EntityNotFoundException Si el autor no existe en el sistema.
     */
    @Override
    public AutorDTO actualizarAutor(AutorDTO autorDTO) {
        if (autorRepository.existsById(autorDTO.getId())) {
            Autor autor = autorMapper.toEntity(autorDTO);
            Autor autorActualizado = autorRepository.save(autor);
            return autorMapper.toDto(autorActualizado);
        } else {
            throw new EntityNotFoundException("Autor", autorDTO.getId());
        }
    }

    /**
     * Elimina un autor por su ID.
     *
     * @param id ID del autor a eliminar.
     * @throws EntityNotFoundException Si el autor no existe en el sistema.
     */
    @Override
    public void eliminarAutor(Long id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Autor", id);
        }
    }

    /**
     * Obtiene una página de autores según los parámetros de paginación.
     *
     * @param pageable Objeto Pageable con los parámetros de paginación.
     * @return Página de AutorDTO con los autores.
     */
    @Override
    public Page<AutorDTO> obtenerAutoresPaginado(Pageable pageable) {
        Page<Autor> autoresPage = autorRepository.findAll(pageable);
        return autoresPage.map(autorMapper::toDto);
    }

    /**
     * Agrupa los autores por nacionalidad.
     *
     * @return Mapa donde la clave es la nacionalidad y el valor es la lista de autores de esa nacionalidad.
     */
    @Override
    public Map<String, List<AutorDTO>> agruparAutoresPorNacionalidad() {
        List<AutorDTO> autores = obtenerAutores();
        return autores.stream()
                .collect(Collectors.groupingBy(AutorDTO::getNacionalidad));
    }
}
