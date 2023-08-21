package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.domain.Administrativo;
import com.bootcamp.ejercicio7m6.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {

    Administrativo findFirstByUsuario(Usuario usuario);

}
