package com.balidea.gestion.libreria.app.repository;

import com.balidea.gestion.libreria.app.model.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}
