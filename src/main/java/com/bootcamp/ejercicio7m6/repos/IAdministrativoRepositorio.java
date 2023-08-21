package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.entidades.Administrativo;
import com.bootcamp.ejercicio7m6.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAdministrativoRepositorio extends JpaRepository<Administrativo, Long> {

    Administrativo findFirstByUsuario(Usuario usuario);

}
