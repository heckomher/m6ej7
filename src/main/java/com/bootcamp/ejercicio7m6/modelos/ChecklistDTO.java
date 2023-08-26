package com.bootcamp.ejercicio7m6.modelos;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ChecklistDTO {

    private Long id;
    private String descripcion;
    private Integer estado;
    private Long visita;

    private Long usuario;
    private String nombreUsuario;
    private LocalDate fechaCheck;
}

