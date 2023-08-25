package com.bootcamp.ejercicio7m6.servicios;

import com.bootcamp.ejercicio7m6.entidades.Checklist;
import com.bootcamp.ejercicio7m6.repositorios.IChecklistRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistServicio {

    private final IChecklistRepositorio checklistRepositorio;

    @Autowired
    public ChecklistServicio(IChecklistRepositorio checklistRepositorio) {
        this.checklistRepositorio = checklistRepositorio;
    }

    public Checklist crearChecklist(String descripcion) {
        Checklist checklist = new Checklist(descripcion);
        return checklistRepositorio.save(checklist);
    }

    public List<Checklist> obtenerTodosLosChecklists() {
        return checklistRepositorio.findAll();
    }

    public Checklist obtenerChecklistPorId(Long id) {
        return checklistRepositorio.findById(id).orElse(null);
    }

    public void eliminarChecklistPorId(Long id) {
        checklistRepositorio.deleteById(id);
    }

    // Puedes agregar más métodos según tus necesidades, como actualización, búsqueda, etc.
}
