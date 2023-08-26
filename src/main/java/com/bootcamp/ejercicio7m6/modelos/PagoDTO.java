package com.bootcamp.ejercicio7m6.modelos;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter

public class PagoDTO {
    private long idPago;

    private Integer monto;
    private LocalDate fechaPago;
    private Long idUsuario;

    private String nombreUsuario;
    private String rutCliente;
}

