package com.bootcamp.ejercicio7m6.rest;

import com.bootcamp.ejercicio7m6.model.ClienteDTO;
import com.bootcamp.ejercicio7m6.service.ClienteService;
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
@RequestMapping(value = "/api/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteResource {

    private final ClienteService clienteService;

    public ClienteResource(final ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable final Long idCliente) {
        return ResponseEntity.ok(clienteService.get(idCliente));
    }

    @PostMapping
    public ResponseEntity<Long> createCliente(@RequestBody @Valid final ClienteDTO clienteDTO) {
        final Long createdIdCliente = clienteService.create(clienteDTO);
        return new ResponseEntity<>(createdIdCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Long> updateCliente(@PathVariable final Long idCliente,
            @RequestBody @Valid final ClienteDTO clienteDTO) {
        clienteService.update(idCliente, clienteDTO);
        return ResponseEntity.ok(idCliente);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deleteCliente(@PathVariable final Long idCliente) {
        clienteService.delete(idCliente);
        return ResponseEntity.noContent().build();
    }

}
