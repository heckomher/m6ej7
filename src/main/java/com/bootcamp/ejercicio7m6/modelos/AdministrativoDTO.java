package com.bootcamp.ejercicio7m6.modelos;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AdministrativoDTO {

    private Long idAdministrativo;

    @Size(max = 255)
    private String area;

    @Size(max = 255)
    private String experienciaPrevia;



    private Long usuario;

}
