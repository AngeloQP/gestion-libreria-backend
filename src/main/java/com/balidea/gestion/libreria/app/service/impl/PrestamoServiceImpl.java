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

@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PrestamoMapper prestamoMapper;

    @Override
    public PrestamoDTO guardarPrestamo(PrestamoDTO prestamoDTO) {
        Prestamo prestamo = prestamoMapper.toEntity(prestamoDTO);
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);
        return prestamoMapper.toDto(prestamoGuardado);
    }

    @Override
    public PrestamoDTO obtenerPrestamoPorId(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prestamo", id));
        return prestamoMapper.toDto(prestamo);
    }

    @Override
    public List<PrestamoDTO> obtenerPrestamos() {
        List<Prestamo> prestamos = prestamoRepository.findAll();

        return prestamoMapper.toDtoList(prestamos);
    }

    @Override
    public PrestamoDTO actualizarPrestamo(PrestamoDTO prestamoDTO) {
        Prestamo prestamo = prestamoRepository.findById(prestamoDTO.getId())
                .map(existingPrestamo -> prestamoMapper.toEntity(prestamoDTO))
                .orElseThrow(() -> new EntityNotFoundException("Prestamo", prestamoDTO.getId()));
        Prestamo prestamoActualizado = prestamoRepository.save(prestamo);
        return prestamoMapper.toDto(prestamoActualizado);
    }

    @Override
    public void eliminarPrestamo(Long id) {
        if (!prestamoRepository.existsById(id)) {
            throw new EntityNotFoundException("Prestamo", id);
        }
        prestamoRepository.deleteById(id);
    }

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
