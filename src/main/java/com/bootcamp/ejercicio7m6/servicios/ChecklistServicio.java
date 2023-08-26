package com.bootcamp.ejercicio7m6.servicios;

import com.bootcamp.ejercicio7m6.entidades.Checklist;
import com.bootcamp.ejercicio7m6.entidades.Usuario;
import com.bootcamp.ejercicio7m6.entidades.Visita;
import com.bootcamp.ejercicio7m6.modelos.ChecklistDTO;
import com.bootcamp.ejercicio7m6.modelos.VisitaDTO;
import com.bootcamp.ejercicio7m6.repos.IUsuarioRepositorio;
import com.bootcamp.ejercicio7m6.repos.IVisitaRepositorio;
import com.bootcamp.ejercicio7m6.repos.IChecklistRepositorio;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistServicio {

    private final IChecklistRepositorio IChecklistRepositorio;
    private final IUsuarioRepositorio IUsuarioRepositorio;
    private final IVisitaRepositorio IVisitaRepositorio;

    @Autowired
    public ChecklistServicio( final IChecklistRepositorio IChecklistRepositorio , final IUsuarioRepositorio IUsuarioRepositorio,
                              final IVisitaRepositorio IVisitaRepositorio) {
        this.IChecklistRepositorio = IChecklistRepositorio;
        this.IUsuarioRepositorio = IUsuarioRepositorio;
        this.IVisitaRepositorio = IVisitaRepositorio;
    }

    public List<ChecklistDTO> findAll() {
        final List<Checklist> checklists = IChecklistRepositorio.findAll(Sort.by("id"));
        return checklists.stream()
                .map(checkList -> mapToDTO(checkList, new ChecklistDTO()))
                .toList();
    }

    public ChecklistDTO get(final Long idVisita) {
        return IChecklistRepositorio.findById(idVisita)
                .map(checkList -> mapToDTO(checkList, new ChecklistDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ChecklistDTO checkListDTO) {
        final Checklist checkList = new Checklist();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Usuario us = IUsuarioRepositorio.findByNombreUsuario(currentPrincipalName);
        checkListDTO.setUsuario(us.getIdUsuario());

        mapToEntity(checkListDTO, checkList);
        return IChecklistRepositorio.save(checkList).getId();
    }



    private ChecklistDTO mapToDTO(final Checklist checklist, final ChecklistDTO checkListDTO) {
        checkListDTO.setUsuario(checklist.getUsuario().getIdUsuario());
        checkListDTO.setVisita( checklist.getVisita().getIdVisita());
        checkListDTO.setNombreUsuario(IUsuarioRepositorio.findById(checklist.getUsuario().getIdUsuario()).orElseThrow().getNombreUsuario() );
        checkListDTO.setDescripcion(checklist.getDescripcion());
        checkListDTO.setEstado(checklist.getEstado());
        checkListDTO.setFechaCheck(checklist.getFechaCheck());

        return checkListDTO;
    }

    private Visita mapToEntity(final ChecklistDTO checklistDTO, final Checklist checklist) {
        checklist.setFechaCheck(checklistDTO.getFechaCheck());
        checklist.setDescripcion(checklistDTO.getDescripcion());

        final Visita visita = checklistDTO.getVisita() == null ? null : IVisitaRepositorio.findById(checklistDTO.getVisita())
                .orElseThrow(() -> new NotFoundException("visita not found"));
        final Usuario usuario = checklistDTO.getUsuario() == null ? null : IUsuarioRepositorio.findById(checklistDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("usuario not found"));

        checklist.setUsuario( usuario );
        checklist.setVisita(visita);
        return visita;
    }

    /*public boolean usuarioExists(final Long idUsuario) {
        return IProfesionalRepositorio.existsByUsuarioIdUsuario(idUsuario);
    }*/

}

