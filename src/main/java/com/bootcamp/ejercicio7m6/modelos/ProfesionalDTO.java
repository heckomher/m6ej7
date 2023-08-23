package com.bootcamp.ejercicio7m6.modelos;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ProfesionalDTO {

    private Long idProfesional;

    @Size(max = 255)
    private String titulo;

    @Size(max = 255)
    private LocalDate fechaIngreso;



    private Long usuario;

}
