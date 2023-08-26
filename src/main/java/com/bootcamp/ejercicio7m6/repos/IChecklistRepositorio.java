package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.entidades.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChecklistRepositorio extends JpaRepository<Checklist, Long> {
    // Aquí puedes agregar métodos personalizados de consulta si los necesitas
}
