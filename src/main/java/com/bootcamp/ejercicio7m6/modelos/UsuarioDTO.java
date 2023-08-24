package com.bootcamp.ejercicio7m6.modelos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UsuarioDTO {

    private Long idUsuario;

    @Size(max = 255)
    private String nombre;

    @NotNull
    @Size(max = 255)
    private String nombreUsuario;

    @NotNull
    private String contrasena;

    @NotNull
    @Size(max = 255)
    private String tipoUsuario;



}
