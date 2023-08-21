package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.domain.Cliente;
import com.bootcamp.ejercicio7m6.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByUsuarioIdUsuario(Long idUsuario);

    Cliente findFirstByUsuario(Usuario usuario);

}
