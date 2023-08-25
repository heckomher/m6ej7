package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {

    boolean existsByNombreUsuarioIgnoreCase(String nombreUsuario);

    Usuario findByNombreUsuario(String nombreUsuario);


}
