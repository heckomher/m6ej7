package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.entidades.Pago;
import com.bootcamp.ejercicio7m6.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPagoRepositorio extends JpaRepository<Pago, Long> {
    Pago findFirstByUsuario(Usuario usuario);
}
