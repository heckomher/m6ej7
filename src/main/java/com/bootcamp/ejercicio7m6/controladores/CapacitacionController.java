package com.bootcamp.ejercicio7m6.controladores;

import com.bootcamp.ejercicio7m6.modelos.CapacitacionDTO;
import com.bootcamp.ejercicio7m6.servicios.CapacitacionServicio;
import com.bootcamp.ejercicio7m6.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/capacitacions")
public class CapacitacionController {

    private final CapacitacionServicio capacitacionServicio;

    public CapacitacionController(final CapacitacionServicio capacitacionServicio) {
        this.capacitacionServicio = capacitacionServicio;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("capacitacions", capacitacionServicio.findAll());
        return "capacitacion/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("capacitacion") final CapacitacionDTO capacitacionDTO) {
        return "capacitacion/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("capacitacion") @Valid final CapacitacionDTO capacitacionDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "capacitacion/add";
        }
        capacitacionServicio.create(capacitacionDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("capacitacion.create.success"));
        return "redirect:/capacitacions";
    }

    @GetMapping("/edit/{numCapacitacion}")
    public String edit(@PathVariable final Integer numCapacitacion, final Model model) {
        model.addAttribute("capacitacion", capacitacionServicio.get(numCapacitacion));
        return "capacitacion/edit";
    }

    @PostMapping("/edit/{numCapacitacion}")
    public String edit(@PathVariable final Integer numCapacitacion,
            @ModelAttribute("capacitacion") @Valid final CapacitacionDTO capacitacionDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "capacitacion/edit";
        }
        capacitacionServicio.update(numCapacitacion, capacitacionDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("capacitacion.update.success"));
        return "redirect:/capacitacions";
    }

    @PostMapping("/delete/{numCapacitacion}")
    public String delete(@PathVariable final Integer numCapacitacion,
            final RedirectAttributes redirectAttributes) {
        capacitacionServicio.delete(numCapacitacion);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("capacitacion.delete.success"));
        return "redirect:/capacitacions";
    }

}
