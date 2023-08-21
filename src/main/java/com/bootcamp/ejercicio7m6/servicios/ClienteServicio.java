package com.bootcamp.ejercicio7m6.servicios;

import com.bootcamp.ejercicio7m6.entidades.Cliente;
import com.bootcamp.ejercicio7m6.entidades.Usuario;
import com.bootcamp.ejercicio7m6.modelos.ClienteDTO;
import com.bootcamp.ejercicio7m6.repos.IClienteRepositorio;
import com.bootcamp.ejercicio7m6.repos.IUsuarioRepositorio;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ClienteServicio {

    private final IClienteRepositorio IClienteRepositorio;
    private final IUsuarioRepositorio IUsuarioRepositorio;

    public ClienteServicio(final IClienteRepositorio IClienteRepositorio,
                           final IUsuarioRepositorio IUsuarioRepositorio) {
        this.IClienteRepositorio = IClienteRepositorio;
        this.IUsuarioRepositorio = IUsuarioRepositorio;
    }

    public List<ClienteDTO> findAll() {
        final List<Cliente> clientes = IClienteRepositorio.findAll(Sort.by("idCliente"));
        return clientes.stream()
                .map(cliente -> mapToDTO(cliente, new ClienteDTO()))
                .toList();
    }

    public ClienteDTO get(final Long idCliente) {
        return IClienteRepositorio.findById(idCliente)
                .map(cliente -> mapToDTO(cliente, new ClienteDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ClienteDTO clienteDTO) {
        final Cliente cliente = new Cliente();
        mapToEntity(clienteDTO, cliente);
        return IClienteRepositorio.save(cliente).getIdCliente();
    }

    public void update(final Long idCliente, final ClienteDTO clienteDTO) {
        final Cliente cliente = IClienteRepositorio.findById(idCliente)
                .orElseThrow(NotFoundException::new);
        mapToEntity(clienteDTO, cliente);
        IClienteRepositorio.save(cliente);
    }

    public void delete(final Long idCliente) {
        IClienteRepositorio.deleteById(idCliente);
    }

    private ClienteDTO mapToDTO(final Cliente cliente, final ClienteDTO clienteDTO) {
        clienteDTO.setIdCliente(cliente.getIdCliente());
        clienteDTO.setAfp(cliente.getAfp());
        clienteDTO.setApellidos(cliente.getApellidos());
        clienteDTO.setComuna(cliente.getComuna());
        clienteDTO.setDireccion(cliente.getDireccion());
        clienteDTO.setEdad(cliente.getEdad());
        clienteDTO.setRut(cliente.getRut());
        clienteDTO.setSistemaSalud(cliente.getSistemaSalud());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setUsuario(cliente.getUsuario() == null ? null : cliente.getUsuario().getIdUsuario());
        return clienteDTO;
    }

    private Cliente mapToEntity(final ClienteDTO clienteDTO, final Cliente cliente) {
        cliente.setAfp(clienteDTO.getAfp());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setComuna(clienteDTO.getComuna());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setEdad(clienteDTO.getEdad());
        cliente.setRut(clienteDTO.getRut());
        cliente.setSistemaSalud(clienteDTO.getSistemaSalud());
        cliente.setTelefono(clienteDTO.getTelefono());
        final Usuario usuario = clienteDTO.getUsuario() == null ? null : IUsuarioRepositorio.findById(clienteDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("usuario not found"));
        cliente.setUsuario(usuario);
        return cliente;
    }

    public boolean usuarioExists(final Long idUsuario) {
        return IClienteRepositorio.existsByUsuarioIdUsuario(idUsuario);
    }

}
