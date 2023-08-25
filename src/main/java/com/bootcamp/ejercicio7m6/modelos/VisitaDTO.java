package com.bootcamp.ejercicio7m6.modelos;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class VisitaDTO {

    private Long idVisita;

    @Size(max = 255)
    private String detalle;

    private LocalDate fechaVisita;

    private Long usuario;
    private Long cliente;

    private String nombreUsuario;
    private String rutCliente;

}
