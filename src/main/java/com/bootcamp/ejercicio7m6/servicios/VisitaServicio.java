package com.bootcamp.ejercicio7m6.servicios;


import com.bootcamp.ejercicio7m6.entidades.Cliente;
import com.bootcamp.ejercicio7m6.entidades.Profesional;
import com.bootcamp.ejercicio7m6.entidades.Usuario;
import com.bootcamp.ejercicio7m6.entidades.Visita;
import com.bootcamp.ejercicio7m6.modelos.ProfesionalDTO;
import com.bootcamp.ejercicio7m6.modelos.VisitaDTO;
import com.bootcamp.ejercicio7m6.repos.IProfesionalRepositorio;
import com.bootcamp.ejercicio7m6.repos.IVisitaRepositorio;
import com.bootcamp.ejercicio7m6.repos.IUsuarioRepositorio;
import com.bootcamp.ejercicio7m6.repos.IClienteRepositorio;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VisitaServicio {

    private final IUsuarioRepositorio IUsuarioRepositorio;
    private final IProfesionalRepositorio IProfesionalRepositorio;
    private final IClienteRepositorio IClienteRepositorio;
    private final IVisitaRepositorio IVisitaRepositorio;

    public VisitaServicio(final IProfesionalRepositorio IProfesionalRepositorio,
                        final IClienteRepositorio IClienteRepositorio, final IVisitaRepositorio IVisitaRepositorio,
                          final IUsuarioRepositorio IUsuarioRepositorio) {
        this.IVisitaRepositorio = IVisitaRepositorio;
        this.IProfesionalRepositorio = IProfesionalRepositorio;
        this.IClienteRepositorio = IClienteRepositorio;
        this.IUsuarioRepositorio = IUsuarioRepositorio;
    }

    public List<VisitaDTO> findAll() {
        final List<Visita> visitas = IVisitaRepositorio.findAll(Sort.by("idVisita"));
        return visitas.stream()
                .map(visita -> mapToDTO(visita, new VisitaDTO()))
                .toList();
    }

    public VisitaDTO get(final Long idVisita) {
        return IVisitaRepositorio.findById(idVisita)
                .map(visita -> mapToDTO(visita, new VisitaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final VisitaDTO visitaDTO) {
        final Visita visita = new Visita();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Usuario us = IUsuarioRepositorio.findByNombreUsuario(currentPrincipalName);
        visitaDTO.setUsuario(us.getIdUsuario());

        mapToEntity(visitaDTO, visita);
        return IVisitaRepositorio.save(visita).getIdVisita();
    }

    public void update(final Long idVisita, final VisitaDTO visitaDTO) {
        final Visita visita = IVisitaRepositorio.findById(idVisita)
                .orElseThrow(NotFoundException::new);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Usuario us = IUsuarioRepositorio.findByNombreUsuario(currentPrincipalName);
        visitaDTO.setUsuario(us.getIdUsuario());
        mapToEntity(visitaDTO, visita);
        IVisitaRepositorio.save(visita);
    }

    public void delete(final Long idVisita) {
        IVisitaRepositorio.deleteById(idVisita);
    }

    private VisitaDTO mapToDTO(final Visita visita, final VisitaDTO visitaDTO) {
        visitaDTO.setUsuario(visita.getUsuario().getIdUsuario());
        visitaDTO.setNombreUsuario(IUsuarioRepositorio.findById(visita.getUsuario().getIdUsuario()).orElseThrow().getNombreUsuario() );
        visitaDTO.setCliente(visita.getCliente().getIdUsuario());
        visitaDTO.setRutCliente(IUsuarioRepositorio.findById(visita.getCliente().getIdUsuario()).orElseThrow().getUsuarioClientes().getRut() );
        visitaDTO.setDetalle(visita.getDetalle());
        visitaDTO.setIdVisita(visita.getIdVisita());
        visitaDTO.setFechaVisita(visita.getFechaVisita());


        return visitaDTO;
    }

    private Visita mapToEntity(final VisitaDTO visitaDTO, final Visita visita) {
        visita.setFechaVisita(visitaDTO.getFechaVisita());
        visita.setDetalle(visitaDTO.getDetalle());

        final Usuario usuario = visitaDTO.getUsuario() == null ? null : IUsuarioRepositorio.findById(visitaDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("usuario not found"));

        final Usuario cliente = visitaDTO.getCliente() == null ? null : IUsuarioRepositorio.findById(visitaDTO.getCliente())
                .orElseThrow(() -> new NotFoundException("cliente not found"));

        visita.setUsuario(usuario);
        visita.setCliente(cliente);
        return visita;
    }

    /*public boolean usuarioExists(final Long idUsuario) {
        return IProfesionalRepositorio.existsByUsuarioIdUsuario(idUsuario);
    }*/

}
