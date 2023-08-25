package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.entidades.Profesional;
import com.bootcamp.ejercicio7m6.entidades.Usuario;
import com.bootcamp.ejercicio7m6.entidades.Visita;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IVisitaRepositorio extends JpaRepository<Visita, Long> {

    Visita findFirstByUsuario(Usuario usuario);
    Visita findFirstByCliente(Usuario cliente);
    //boolean existsByUsuarioIdUsuario(Long idUsuario);
}
