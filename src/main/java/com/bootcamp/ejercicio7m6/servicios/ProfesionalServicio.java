package com.bootcamp.ejercicio7m6.servicios;


import com.bootcamp.ejercicio7m6.entidades.Profesional;
import com.bootcamp.ejercicio7m6.entidades.Usuario;

import com.bootcamp.ejercicio7m6.modelos.ProfesionalDTO;

import com.bootcamp.ejercicio7m6.repos.IProfesionalRepositorio;
import com.bootcamp.ejercicio7m6.repos.IUsuarioRepositorio;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProfesionalServicio {
    private final IProfesionalRepositorio IProfesionalRepositorio;
    private final IUsuarioRepositorio IUsuarioRepositorio;

    public ProfesionalServicio(final IProfesionalRepositorio IProfesionalRepositorio,
                           final IUsuarioRepositorio IUsuarioRepositorio) {
        this.IProfesionalRepositorio = IProfesionalRepositorio;
        this.IUsuarioRepositorio = IUsuarioRepositorio;
    }

    public List<ProfesionalDTO> findAll() {
        final List<Profesional> profesionals = IProfesionalRepositorio.findAll(Sort.by("idProfesional"));
        return profesionals.stream()
                .map(profesional -> mapToDTO(profesional, new ProfesionalDTO()))
                .toList();
    }

    public ProfesionalDTO get(final Long idProfesional) {
        return IProfesionalRepositorio.findById(idProfesional)
                .map(profesional -> mapToDTO(profesional, new ProfesionalDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProfesionalDTO profesionalDTO) {
        final Profesional profesional = new Profesional();
        mapToEntity(profesionalDTO, profesional);
        return IProfesionalRepositorio.save(profesional).getIdProfesional();
    }

    public void update(final Long idProfesional, final ProfesionalDTO profesionalDTO) {
        final Profesional profesional = IProfesionalRepositorio.findById(idProfesional)
                .orElseThrow(NotFoundException::new);
        mapToEntity(profesionalDTO, profesional);
        IProfesionalRepositorio.save(profesional);
    }

    public void delete(final Long idProfesional) {
        IProfesionalRepositorio.deleteById(idProfesional);
    }

    private ProfesionalDTO mapToDTO(final Profesional profesional, final ProfesionalDTO profesionalDTO) {
        profesionalDTO.setIdProfesional(profesional.getIdProfesional());
        profesionalDTO.setTitulo(profesional.getTitulo());
        profesionalDTO.setFechaIngreso(profesional.getFechaIngreso());

        profesionalDTO.setUsuario(profesional.getUsuario() == null ? null : profesional.getUsuario().getIdUsuario());
        return profesionalDTO;
    }

    private Profesional mapToEntity(final ProfesionalDTO profesionalDTO, final Profesional profesional) {
        profesional.setTitulo(profesionalDTO.getTitulo());
        profesional.setFechaIngreso(profesionalDTO.getFechaIngreso());

        final Usuario usuario = profesionalDTO.getUsuario() == null ? null : IUsuarioRepositorio.findById(profesionalDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("usuario not found"));
        profesional.setUsuario(usuario);
        return profesional;
    }

    public boolean usuarioExists(final Long idUsuario) {
        return IProfesionalRepositorio.existsByUsuarioIdUsuario(idUsuario);
    }

}
