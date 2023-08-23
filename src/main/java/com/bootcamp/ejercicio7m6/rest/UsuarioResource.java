package com.bootcamp.ejercicio7m6.rest;

import com.bootcamp.ejercicio7m6.modelos.UsuarioDTO;
import com.bootcamp.ejercicio7m6.servicios.UsuarioServicio;
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
@RequestMapping(value = "/api/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioResource {

    private final UsuarioServicio usuarioServicio;

    public UsuarioResource(final UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/")
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioServicio.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable final Long idUsuario) {
        return ResponseEntity.ok(usuarioServicio.get(idUsuario));
    }

    @PostMapping
    public ResponseEntity<Long> createUsuario(@RequestBody @Valid final UsuarioDTO usuarioDTO) {
        final Long createdIdUsuario = usuarioServicio.create(usuarioDTO);
        return new ResponseEntity<>(createdIdUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Long> updateUsuario(@PathVariable final Long idUsuario,
            @RequestBody @Valid final UsuarioDTO usuarioDTO) {
        usuarioServicio.update(idUsuario, usuarioDTO);
        return ResponseEntity.ok(idUsuario);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable final Long idUsuario) {
        usuarioServicio.delete(idUsuario);
        return ResponseEntity.noContent().build();
    }

}
