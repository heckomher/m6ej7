package com.bootcamp.ejercicio7m6.service;

import com.bootcamp.ejercicio7m6.domain.Administrativo;
import com.bootcamp.ejercicio7m6.domain.Cliente;
import com.bootcamp.ejercicio7m6.domain.Profesional;
import com.bootcamp.ejercicio7m6.domain.Usuario;
import com.bootcamp.ejercicio7m6.model.UsuarioDTO;
import com.bootcamp.ejercicio7m6.repos.AdministrativoRepository;
import com.bootcamp.ejercicio7m6.repos.ClienteRepository;
import com.bootcamp.ejercicio7m6.repos.ProfesionalRepository;
import com.bootcamp.ejercicio7m6.repos.UsuarioRepository;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import com.bootcamp.ejercicio7m6.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AdministrativoRepository administrativoRepository;
    private final ClienteRepository clienteRepository;
    private final ProfesionalRepository profesionalRepository;

    public UsuarioService(final UsuarioRepository usuarioRepository,
            final AdministrativoRepository administrativoRepository,
            final ClienteRepository clienteRepository,
            final ProfesionalRepository profesionalRepository) {
        this.usuarioRepository = usuarioRepository;
        this.administrativoRepository = administrativoRepository;
        this.clienteRepository = clienteRepository;
        this.profesionalRepository = profesionalRepository;
    }

    public List<UsuarioDTO> findAll() {
        final List<Usuario> usuarios = usuarioRepository.findAll(Sort.by("idUsuario"));
        return usuarios.stream()
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .toList();
    }

    public UsuarioDTO get(final Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UsuarioDTO usuarioDTO) {
        final Usuario usuario = new Usuario();
        mapToEntity(usuarioDTO, usuario);
        return usuarioRepository.save(usuario).getIdUsuario();
    }

    public void update(final Long idUsuario, final UsuarioDTO usuarioDTO) {
        final Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usuarioDTO, usuario);
        usuarioRepository.save(usuario);
    }

    public void delete(final Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    private UsuarioDTO mapToDTO(final Usuario usuario, final UsuarioDTO usuarioDTO) {
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
        usuarioDTO.setTipoUsuario(usuario.getTipoUsuario());
        return usuarioDTO;
    }

    private Usuario mapToEntity(final UsuarioDTO usuarioDTO, final Usuario usuario) {
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
        return usuario;
    }

    public boolean nombreUsuarioExists(final String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuarioIgnoreCase(nombreUsuario);
    }

    public String getReferencedWarning(final Long idUsuario) {
        final Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(NotFoundException::new);
        final Administrativo usuarioAdministrativo = administrativoRepository.findFirstByUsuario(usuario);
        if (usuarioAdministrativo != null) {
            return WebUtils.getMessage("usuario.administrativo.usuario.referenced", usuarioAdministrativo.getIdAdministrativo());
        }
        final Cliente usuarioCliente = clienteRepository.findFirstByUsuario(usuario);
        if (usuarioCliente != null) {
            return WebUtils.getMessage("usuario.cliente.usuario.referenced", usuarioCliente.getIdCliente());
        }
        final Profesional usuarioProfesional = profesionalRepository.findFirstByUsuario(usuario);
        if (usuarioProfesional != null) {
            return WebUtils.getMessage("usuario.profesional.usuario.referenced", usuarioProfesional.getIdProfesional());
        }
        return null;
    }

}
