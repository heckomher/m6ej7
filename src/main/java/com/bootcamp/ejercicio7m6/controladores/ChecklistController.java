package com.bootcamp.ejercicio7m6.controladores;

import com.bootcamp.ejercicio7m6.entidades.Checklist;
import com.bootcamp.ejercicio7m6.modelos.ChecklistDTO;
import com.bootcamp.ejercicio7m6.modelos.ClienteDTO;
import com.bootcamp.ejercicio7m6.modelos.VisitaDTO;
import com.bootcamp.ejercicio7m6.servicios.ChecklistServicio;
import com.bootcamp.ejercicio7m6.servicios.ClienteServicio;
import com.bootcamp.ejercicio7m6.servicios.UsuarioServicio;
import com.bootcamp.ejercicio7m6.servicios.VisitaServicio;
import com.bootcamp.ejercicio7m6.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/checklists")
public class ChecklistController {

    private final UsuarioServicio usuarioServicio;

    private final VisitaServicio visitaServicio;
    private final ChecklistServicio checklistServicio;


    public ChecklistController(final UsuarioServicio usuarioServicio, VisitaServicio visitaServicio , ChecklistServicio checklistServicio) {
        this.usuarioServicio = usuarioServicio;
        this.visitaServicio = visitaServicio;
        this.checklistServicio = checklistServicio;

    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("checklists", checklistServicio.findAll());
        return "checklist/list";
    }



    @GetMapping("/add")
    public String add(@ModelAttribute("checklist") final ChecklistDTO checklistDTO , final Model model ) {
        List<VisitaDTO> visitas = visitaServicio.findAll();
        model.addAttribute("visitas" , visitas);
        return "checklist/add";
    }


    @PostMapping("/add")
    public String add(@ModelAttribute("checklist") @Valid final ChecklistDTO checklistDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {


        long idChecklist = checklistServicio.create(checklistDTO);

        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("checklist.create.success"));
        return "redirect:/checklists";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        //usuarioDTO
        model.addAttribute("checklist", checklistServicio.get(id));
        List<VisitaDTO> visitas = visitaServicio.findAll();
        model.addAttribute("visitas" , visitas);

        return "checklist/edit";
    }

/*
    @PostMapping("/edit/{idVisita}")
    public String edit(@PathVariable final Long idVisita,
                       @ModelAttribute("visita") @Valid final VisitaDTO visitaDTO,

                       final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

        visitaServicio.update(idVisita, visitaDTO );

        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("visita.update.success"));
        return "redirect:/visitas";
    }

*/
}
