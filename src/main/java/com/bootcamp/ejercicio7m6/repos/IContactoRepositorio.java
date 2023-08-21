package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.entidades.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IContactoRepositorio extends JpaRepository<Contacto, Integer> {
}
