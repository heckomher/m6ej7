package com.bootcamp.ejercicio7m6.controller;

import com.bootcamp.ejercicio7m6.model.CapacitacionDTO;
import com.bootcamp.ejercicio7m6.service.CapacitacionService;
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

    private final CapacitacionService capacitacionService;

    public CapacitacionController(final CapacitacionService capacitacionService) {
        this.capacitacionService = capacitacionService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("capacitacions", capacitacionService.findAll());
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
        capacitacionService.create(capacitacionDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("capacitacion.create.success"));
        return "redirect:/capacitacions";
    }

    @GetMapping("/edit/{numCapacitacion}")
    public String edit(@PathVariable final Integer numCapacitacion, final Model model) {
        model.addAttribute("capacitacion", capacitacionService.get(numCapacitacion));
        return "capacitacion/edit";
    }

    @PostMapping("/edit/{numCapacitacion}")
    public String edit(@PathVariable final Integer numCapacitacion,
            @ModelAttribute("capacitacion") @Valid final CapacitacionDTO capacitacionDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "capacitacion/edit";
        }
        capacitacionService.update(numCapacitacion, capacitacionDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("capacitacion.update.success"));
        return "redirect:/capacitacions";
    }

    @PostMapping("/delete/{numCapacitacion}")
    public String delete(@PathVariable final Integer numCapacitacion,
            final RedirectAttributes redirectAttributes) {
        capacitacionService.delete(numCapacitacion);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("capacitacion.delete.success"));
        return "redirect:/capacitacions";
    }

}
