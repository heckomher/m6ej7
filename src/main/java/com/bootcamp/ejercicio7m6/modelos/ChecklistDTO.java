package com.bootcamp.ejercicio7m6.modelos;

import java.time.LocalTime;
import java.time.OffsetDateTime;

public class ChecklistDTO {

    private Integer numCapacitacion;
    private Integer cantidadAsistentes;
    private String detalle;
    private String diaSemana;
    private LocalTime duracion;
    private LocalTime hora;
    private String lugar;
    private String nombre;
    private OffsetDateTime dateCreated;
    private OffsetDateTime lastUpdated;

    // Constructors, getters, setters, and other methods can be added here

    // Constructor
    public ChecklistDTO(Integer numCapacitacion, Integer cantidadAsistentes, String detalle, String diaSemana,
                        LocalTime duracion, LocalTime hora, String lugar, String nombre,
                        OffsetDateTime dateCreated, OffsetDateTime lastUpdated) {
        this.numCapacitacion = numCapacitacion;
        this.cantidadAsistentes = cantidadAsistentes;
        this.detalle = detalle;
        this.diaSemana = diaSemana;
        this.duracion = duracion;
        this.hora = hora;
        this.lugar = lugar;
        this.nombre = nombre;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    // Getters and Setters
    public Integer getNumCapacitacion() {
        return numCapacitacion;
    }

    public void setNumCapacitacion(Integer numCapacitacion) {
        this.numCapacitacion = numCapacitacion;
    }

    public Integer getCantidadAsistentes() {
        return cantidadAsistentes;
    }

    public void setCantidadAsistentes(Integer cantidadAsistentes) {
        this.cantidadAsistentes = cantidadAsistentes;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

