package com.bootcamp.ejercicio7m6.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuarioDTO {

    private Long idUsuario;

    @Size(max = 255)
    private String apellido;

    @Size(max = 255)
    private String nombre;

    @NotNull
    @Size(max = 255)
    private String nombreUsuario;

    @NotNull
    @Size(max = 255)
    private String tipoUsuario;

}
