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

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private AutorMapper autorMapper;

    @Override
    public AutorDTO guardarAutor(AutorDTO autorDTO) {
        Autor autor = autorMapper.toEntity(autorDTO);

        Autor autorGuardado = autorRepository.save(autor);

        return autorMapper.toDto(autorGuardado);
    }

    @Override
    public AutorDTO obtenerAutorPorId(Long id) {
        Optional<Autor> autor = autorRepository.findById(id);

        return autor.map(autorMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Autor", id));
    }

    @Override
    public List<AutorDTO> obtenerAutores() {
        List<Autor> autores = autorRepository.findAll();

        return autorMapper.toDtoList(autores);
    }

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

    @Override
    public void eliminarAutor(Long id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Autor", id);
        }
    }

    @Override
    public Page<AutorDTO> obtenerAutoresPaginado(Pageable pageable) {
        Page<Autor> autoresPage = autorRepository.findAll(pageable);

        return autoresPage.map(autorMapper::toDto);
    }

    @Override
    public Map<String, List<AutorDTO>> agruparAutoresPorNacionalidad() {
        List<AutorDTO> autores = obtenerAutores();

        return autores.stream()
                .collect(Collectors.groupingBy(AutorDTO::getNacionalidad));
    }
}
