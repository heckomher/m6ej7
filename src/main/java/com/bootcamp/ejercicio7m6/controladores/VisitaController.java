package com.bootcamp.ejercicio7m6.controladores;

import com.bootcamp.ejercicio7m6.modelos.*;
import com.bootcamp.ejercicio7m6.servicios.*;
import com.bootcamp.ejercicio7m6.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/visitas")
public class VisitaController {

    private final UsuarioServicio usuarioServicio;

    private final VisitaServicio visitaServicio;
    private final ClienteServicio clienteServicio;


    public VisitaController(final UsuarioServicio usuarioServicio, VisitaServicio visitaServicio, ClienteServicio clienteServicio) {
        this.usuarioServicio = usuarioServicio;
        this.visitaServicio = visitaServicio;
        this.clienteServicio = clienteServicio;

    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("visitas", visitaServicio.findAll());
        return "visita/list";
    }


    @GetMapping("/add")
    public String add(@ModelAttribute("visita") final VisitaDTO visitaDTO, final Model model) {
        List<ClienteDTO> clientes = clienteServicio.findAll();
        model.addAttribute("clientes", clientes);
        return "visita/add";
    }

    /*
        @ModelAttribute("tipoUsuarioValues")
        public List<String> tipoUsuarioValues() {
            return Arrays.asList("Administrativo", "Cliente", "Profesional"); // Definir tus valores aquí
        }
    */
    @PostMapping("/add")
    public String add(@ModelAttribute("visita") @Valid final VisitaDTO visitaDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {


        long idVisita = visitaServicio.create(visitaDTO);


        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("visita.create.success"));
        return "redirect:/visitas";
    }


    @GetMapping("/edit/{idVisita}")
    public String edit(@PathVariable final Long idVisita, final Model model) {
        //usuarioDTO
        model.addAttribute("visita", visitaServicio.get(idVisita));
        List<ClienteDTO> clientes = clienteServicio.findAll();
        model.addAttribute("clientes", clientes);

        return "visita/edit";
    }


    @PostMapping("/edit/{idVisita}")
    public String edit(@PathVariable final Long idVisita,
                       @ModelAttribute("visita") @Valid final VisitaDTO visitaDTO,

                       final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

        visitaServicio.update(idVisita, visitaDTO);

        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("visita.update.success"));
        return "redirect:/visitas";
    }



/*
    @PostMapping("/delete/{idUsuario}")
    public String delete(@PathVariable final Long idUsuario,
                         final RedirectAttributes redirectAttributes) {
        final String referencedWarning = usuarioServicio.getReferencedWarning(idUsuario);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            usuarioServicio.delete(idUsuario);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("usuario.delete.success"));
        }
        return "redirect:/usuarios";
    }
*/
}