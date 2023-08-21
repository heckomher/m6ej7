package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.entidades.Profesional;
import com.bootcamp.ejercicio7m6.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProfesionalRepositorio extends JpaRepository<Profesional, Long> {

    Profesional findFirstByUsuario(Usuario usuario);

}
