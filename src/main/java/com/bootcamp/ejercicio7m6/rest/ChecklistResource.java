package com.bootcamp.ejercicio7m6.recursos;

import com.bootcamp.ejercicio7m6.entidades.Checklist;
import com.bootcamp.ejercicio7m6.servicios.ChecklistServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checklists")
public class ChecklistResource {

    private final ChecklistServicio checklistServicio;

    @Autowired
    public ChecklistResource(ChecklistServicio checklistServicio) {
        this.checklistServicio = checklistServicio;
    }

    @GetMapping
    public ResponseEntity<List<Checklist>> obtenerTodosLosChecklists() {
        List<Checklist> checklists = checklistServicio.obtenerTodosLosChecklists();
        return ResponseEntity.ok(checklists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checklist> obtenerChecklistPorId(@PathVariable Long id) {
        Checklist checklist = checklistServicio.obtenerChecklistPorId(id);
        if (checklist != null) {
            return ResponseEntity.ok(checklist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Checklist> crearChecklist(@RequestBody Checklist checklist) {
        Checklist nuevoChecklist = checklistServicio.crearChecklist(checklist.getDescripcion());
        return ResponseEntity.ok(nuevoChecklist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarChecklistPorId(@PathVariable Long id) {
        checklistServicio.eliminarChecklistPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Puedes agregar más endpoints según tus necesidades, como actualización, búsqueda, etc.
}
