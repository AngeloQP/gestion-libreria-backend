package com.balidea.gestion.libreria.app.service.impl;

import com.balidea.gestion.libreria.app.exception.EntityNotFoundException;
import com.balidea.gestion.libreria.app.mapper.PrestamoMapper;
import com.balidea.gestion.libreria.app.model.dto.PrestamoDTO;
import com.balidea.gestion.libreria.app.model.entity.Prestamo;
import com.balidea.gestion.libreria.app.repository.PrestamoRepository;
import com.balidea.gestion.libreria.app.service.PrestamoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz {@link PrestamoService} para la gestión de entidades {@link Prestamo}.
 * Este servicio proporciona métodos para crear, obtener, actualizar, eliminar y paginar los registros de préstamos (prestamos).
 */
@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PrestamoMapper prestamoMapper;

    /**
     * Guarda un nuevo registro de préstamo.
     *
     * @param prestamoDTO el DTO que contiene los datos del préstamo a guardar
     * @return el DTO que representa el préstamo guardado
     */
    @Override
    public PrestamoDTO guardarPrestamo(PrestamoDTO prestamoDTO) {
        Prestamo prestamo = prestamoMapper.toEntity(prestamoDTO);
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);
        return prestamoMapper.toDto(prestamoGuardado);
    }

    /**
     * Obtiene un préstamo por su identificador.
     *
     * @param id el identificador del préstamo
     * @return el DTO que representa el préstamo encontrado
     * @throws EntityNotFoundException si no se encuentra un préstamo con el identificador proporcionado
     */
    @Override
    public PrestamoDTO obtenerPrestamoPorId(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prestamo", id));
        return prestamoMapper.toDto(prestamo);
    }

    /**
     * Obtiene todos los préstamos.
     *
     * @return una lista de DTOs que representan todos los préstamos
     */
    @Override
    public List<PrestamoDTO> obtenerPrestamos() {
        List<Prestamo> prestamos = prestamoRepository.findAll();
        return prestamoMapper.toDtoList(prestamos);
    }

    /**
     * Actualiza un préstamo existente.
     *
     * @param prestamoDTO el DTO que contiene los datos del préstamo a actualizar
     * @return el DTO que representa el préstamo actualizado
     * @throws EntityNotFoundException si no se encuentra un préstamo con el identificador proporcionado
     */
    @Override
    public PrestamoDTO actualizarPrestamo(PrestamoDTO prestamoDTO) {
        Prestamo prestamo = prestamoRepository.findById(prestamoDTO.getId())
                .map(existingPrestamo -> prestamoMapper.toEntity(prestamoDTO))
                .orElseThrow(() -> new EntityNotFoundException("Prestamo", prestamoDTO.getId()));
        Prestamo prestamoActualizado = prestamoRepository.save(prestamo);
        return prestamoMapper.toDto(prestamoActualizado);
    }

    /**
     * Elimina un préstamo por su identificador.
     *
     * @param id el identificador del préstamo a eliminar
     * @throws EntityNotFoundException si no se encuentra un préstamo con el identificador proporcionado
     */
    @Override
    public void eliminarPrestamo(Long id) {
        if (!prestamoRepository.existsById(id)) {
            throw new EntityNotFoundException("Prestamo", id);
        }
        prestamoRepository.deleteById(id);
    }

    /**
     * Obtiene una lista de préstamos asociados a un libro específico.
     *
     * @param idLibro el identificador del libro
     * @return una lista de DTOs que representan los préstamos relacionados con el libro
     */
    @Override
    public List<PrestamoDTO> obtenerPrestamoPorLibro(Long idLibro) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Prestamo> criteriaQuery = criteriaBuilder.createQuery(Prestamo.class);
        Root<Prestamo> prestamoRoot = criteriaQuery.from(Prestamo.class);

        Predicate libroPredicate = criteriaBuilder.equal(prestamoRoot.get("libro").get("id"), idLibro);
        criteriaQuery.where(libroPredicate);

        List<Prestamo> prestamos = entityManager.createQuery(criteriaQuery).getResultList();

        return prestamos.stream()
                .map(prestamoMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una lista de préstamos paginados.
     *
     * @param pageable el objeto Pageable que contiene la información de paginación
     * @return una página de DTOs que representan los préstamos
     */
    @Override
    public Page<PrestamoDTO> obtenerPrestamosPaginado(Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Prestamo> criteriaQuery = criteriaBuilder.createQuery(Prestamo.class);
        Root<Prestamo> prestamoRoot = criteriaQuery.from(Prestamo.class);
        criteriaQuery.select(prestamoRoot);

        List<Prestamo> prestamos = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        long total = prestamoRepository.count();

        List<PrestamoDTO> prestamoDTOs = prestamos.stream()
                .map(prestamoMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(prestamoDTOs, pageable, total);
    }
}
