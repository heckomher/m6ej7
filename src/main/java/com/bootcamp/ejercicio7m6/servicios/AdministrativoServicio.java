package com.bootcamp.ejercicio7m6.servicios;

import com.bootcamp.ejercicio7m6.entidades.Administrativo;
import com.bootcamp.ejercicio7m6.entidades.Profesional;
import com.bootcamp.ejercicio7m6.entidades.Usuario;
import com.bootcamp.ejercicio7m6.modelos.AdministrativoDTO;
import com.bootcamp.ejercicio7m6.modelos.ProfesionalDTO;
import com.bootcamp.ejercicio7m6.repos.IAdministrativoRepositorio;
import com.bootcamp.ejercicio7m6.repos.IUsuarioRepositorio;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrativoServicio {

    private final IAdministrativoRepositorio IAdministrativoRepositorio;
    private final IUsuarioRepositorio IUsuarioRepositorio;

    public AdministrativoServicio(final IAdministrativoRepositorio IAdministrativoRepositorio,
                               final IUsuarioRepositorio IUsuarioRepositorio) {
        this.IAdministrativoRepositorio = IAdministrativoRepositorio;
        this.IUsuarioRepositorio = IUsuarioRepositorio;
    }

    public List<AdministrativoDTO> findAll() {
        final List<Administrativo> administrativos = IAdministrativoRepositorio.findAll(Sort.by("idAdministrativo"));
        return administrativos.stream()
                .map(administrativo -> mapToDTO(administrativo, new AdministrativoDTO()))
                .toList();
    }

    public AdministrativoDTO get(final Long idAdministrativo) {
        return IAdministrativoRepositorio.findById(idAdministrativo)
                .map(administrativo -> mapToDTO(administrativo, new AdministrativoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final AdministrativoDTO administrativoDTO) {
        final Administrativo administrativo = new Administrativo();
        mapToEntity(administrativoDTO, administrativo);
        return IAdministrativoRepositorio.save(administrativo).getIdAdministrativo();
    }

    public void update(final Long idAdministrativo, final AdministrativoDTO administrativoDTO) {
        final Administrativo administrativo = IAdministrativoRepositorio.findById(idAdministrativo)
                .orElseThrow(NotFoundException::new);
        mapToEntity(administrativoDTO, administrativo);
        IAdministrativoRepositorio.save(administrativo);
    }

    public void delete(final Long idAdministrativo) {
        IAdministrativoRepositorio.deleteById(idAdministrativo);
    }

    private AdministrativoDTO mapToDTO(final Administrativo administrativo, final AdministrativoDTO administrativoDTO) {
        administrativoDTO.setIdAdministrativo(administrativo.getIdAdministrativo());
        administrativoDTO.setArea(administrativo.getArea());
        administrativoDTO.setExperienciaPrevia(administrativo.getExperienciaPrevia());

        administrativoDTO.setUsuario(administrativo.getUsuario() == null ? null : administrativo.getUsuario().getIdUsuario());
        return administrativoDTO;
    }

    private Administrativo mapToEntity(final AdministrativoDTO administrativoDTO, final Administrativo administrativo) {
        administrativo.setArea(administrativoDTO.getArea());
        administrativo.setExperienciaPrevia(administrativoDTO.getExperienciaPrevia());

        final Usuario usuario = administrativoDTO.getUsuario() == null ? null : IUsuarioRepositorio.findById(administrativoDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        administrativo.setUsuario(usuario);
        return administrativo;
    }

    public boolean usuarioExists(final Long idUsuario) {
        return IAdministrativoRepositorio.existsByUsuarioIdUsuario(idUsuario);
    }

}
