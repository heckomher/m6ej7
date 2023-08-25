package com.bootcamp.ejercicio7m6.servicios;

import com.bootcamp.ejercicio7m6.entidades.Administrativo;
import com.bootcamp.ejercicio7m6.entidades.Cliente;
import com.bootcamp.ejercicio7m6.entidades.Profesional;
import com.bootcamp.ejercicio7m6.entidades.Usuario;
import com.bootcamp.ejercicio7m6.modelos.UsuarioDTO;
import com.bootcamp.ejercicio7m6.repos.IAdministrativoRepositorio;
import com.bootcamp.ejercicio7m6.repos.IClienteRepositorio;
import com.bootcamp.ejercicio7m6.repos.IProfesionalRepositorio;
import com.bootcamp.ejercicio7m6.repos.IUsuarioRepositorio;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import com.bootcamp.ejercicio7m6.util.WebUtils;
import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServicio{

    private final IUsuarioRepositorio IUsuarioRepositorio;
    private final IAdministrativoRepositorio IAdministrativoRepositorio;
    private final IClienteRepositorio IClienteRepositorio;
    private final IProfesionalRepositorio IProfesionalRepositorio;

    public UsuarioServicio(final IUsuarioRepositorio IUsuarioRepositorio,
                           final IAdministrativoRepositorio IAdministrativoRepositorio,
                           final IClienteRepositorio IClienteRepositorio,
                           final IProfesionalRepositorio IProfesionalRepositorio) {
        this.IUsuarioRepositorio = IUsuarioRepositorio;
        this.IAdministrativoRepositorio = IAdministrativoRepositorio;
        this.IClienteRepositorio = IClienteRepositorio;
        this.IProfesionalRepositorio = IProfesionalRepositorio;
    }

    public List<UsuarioDTO> findAll() {
        final List<Usuario> usuarios = IUsuarioRepositorio.findAll(Sort.by("idUsuario"));
        return usuarios.stream()
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .toList();
    }

    public Usuario findById(long idUsuario) {
        return IUsuarioRepositorio.findById(idUsuario).orElseThrow();
    }

    public UsuarioDTO get(final Long idUsuario) {
        return IUsuarioRepositorio.findById(idUsuario)
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UsuarioDTO usuarioDTO) {
        final Usuario usuario = new Usuario();
        mapToEntity(usuarioDTO, usuario);


        return IUsuarioRepositorio.save(usuario).getIdUsuario();
    }

    public void update(final Long idUsuario, final UsuarioDTO usuarioDTO) {
        final Usuario usuario = IUsuarioRepositorio.findById(idUsuario)
                .orElseThrow(NotFoundException::new);

        mapToEntity(usuarioDTO, usuario);
        IUsuarioRepositorio.save(usuario);
    }

    public void delete(final Long idUsuario) {
        IUsuarioRepositorio.deleteById(idUsuario);
    }

    private UsuarioDTO mapToDTO(final Usuario usuario, final UsuarioDTO usuarioDTO) {
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());

        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
        usuarioDTO.setTipoUsuario(usuario.getTipoUsuario());
        usuarioDTO.setContrasena(usuario.getContrasena());

        return usuarioDTO;
    }

    private Usuario mapToEntity(final UsuarioDTO usuarioDTO, final Usuario usuario) {

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
        usuario.setContrasena(usuarioDTO.getContrasena());
        return usuario;
    }

    public boolean nombreUsuarioExists(final String nombreUsuario) {
        return IUsuarioRepositorio.existsByNombreUsuarioIgnoreCase(nombreUsuario);
    }

    public String getReferencedWarning(final Long idUsuario) {
        final Usuario usuario = IUsuarioRepositorio.findById(idUsuario)
                .orElseThrow(NotFoundException::new);
        final Administrativo usuarioAdministrativo = IAdministrativoRepositorio.findFirstByUsuario(usuario);
        if (usuarioAdministrativo != null) {
            return WebUtils.getMessage("usuario.administrativo.usuario.referenced", usuarioAdministrativo.getIdAdministrativo());
        }
        final Cliente usuarioCliente = IClienteRepositorio.findFirstByUsuario(usuario);
        if (usuarioCliente != null) {
            return WebUtils.getMessage("usuario.cliente.usuario.referenced", usuarioCliente.getIdCliente());
        }
        final Profesional usuarioProfesional = IProfesionalRepositorio.findFirstByUsuario(usuario);
        if (usuarioProfesional != null) {
            return WebUtils.getMessage("usuario.profesional.usuario.referenced", usuarioProfesional.getIdProfesional());
        }
        return null;
    }
}
