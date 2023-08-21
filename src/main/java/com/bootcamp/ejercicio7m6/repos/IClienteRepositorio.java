package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.entidades.Cliente;
import com.bootcamp.ejercicio7m6.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IClienteRepositorio extends JpaRepository<Cliente, Long> {

    boolean existsByUsuarioIdUsuario(Long idUsuario);

    Cliente findFirstByUsuario(Usuario usuario);

}
