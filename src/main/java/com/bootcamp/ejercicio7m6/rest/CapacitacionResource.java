package com.bootcamp.ejercicio7m6.rest;

import com.bootcamp.ejercicio7m6.modelos.CapacitacionDTO;
import com.bootcamp.ejercicio7m6.servicios.CapacitacionServicio;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/capacitaciones", produces = MediaType.APPLICATION_JSON_VALUE)
public class CapacitacionResource {

    private final CapacitacionServicio capacitacionServicio;

    public CapacitacionResource(final CapacitacionServicio capacitacionServicio) {
        this.capacitacionServicio = capacitacionServicio;
    }

    @GetMapping
    public ResponseEntity<List<CapacitacionDTO>> getAllCapacitacions() {
        return ResponseEntity.ok(capacitacionServicio.findAll());
    }

    @GetMapping("/{numCapacitacion}")
    public ResponseEntity<CapacitacionDTO> getCapacitacion(
            @PathVariable final Integer numCapacitacion) {
        return ResponseEntity.ok(capacitacionServicio.get(numCapacitacion));
    }

    @PostMapping
    public ResponseEntity<CapacitacionDTO> createCapacitacion(
            @RequestBody @Valid final CapacitacionDTO capacitacionDTO) {
        final Integer createdNumCapacitacion = capacitacionServicio.create(capacitacionDTO);
        return new ResponseEntity<>(capacitacionServicio.get(createdNumCapacitacion), HttpStatus.CREATED);
    }

    @PutMapping("/{numCapacitacion}")
    public ResponseEntity<Integer> updateCapacitacion(@PathVariable final Integer numCapacitacion,
            @RequestBody @Valid final CapacitacionDTO capacitacionDTO) {
        capacitacionServicio.update(numCapacitacion, capacitacionDTO);
        return ResponseEntity.ok(numCapacitacion);
    }

    @DeleteMapping("/{numCapacitacion}")
    public ResponseEntity<Void> deleteCapacitacion(@PathVariable final Integer numCapacitacion) {
        capacitacionServicio.delete(numCapacitacion);
        return ResponseEntity.noContent().build();
    }

}
