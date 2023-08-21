package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.domain.Profesional;
import com.bootcamp.ejercicio7m6.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {

    Profesional findFirstByUsuario(Usuario usuario);

}
