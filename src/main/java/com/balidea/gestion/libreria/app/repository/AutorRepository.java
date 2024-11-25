package com.balidea.gestion.libreria.app.repository;

import com.balidea.gestion.libreria.app.model.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
