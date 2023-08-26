package com.bootcamp.ejercicio7m6.controladores;

import com.bootcamp.ejercicio7m6.modelos.AdministrativoDTO;
import com.bootcamp.ejercicio7m6.modelos.ClienteDTO;
import com.bootcamp.ejercicio7m6.modelos.ProfesionalDTO;
import com.bootcamp.ejercicio7m6.modelos.UsuarioDTO;
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



    public VisitaController(final UsuarioServicio usuarioServicio, VisitaServicio visitaServicio , ClienteServicio clienteServicio) {
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
    public String add(@ModelAttribute("visita") final VisitaDTO visitaDTO , final Model model ) {
        List<ClienteDTO> clientes = clienteServicio.findAll();
        model.addAttribute("clientes" , clientes);
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

        if (!bindingResult.hasFieldErrors("nombreUsuario") && usuarioServicio.nombreUsuarioExists(usuarioDTO.getNombreUsuario())) {
            bindingResult.rejectValue("nombreUsuario", "Exists.usuario.nombreUsuario");
        }
        if (bindingResult.hasErrors()) {
            return "usuario/add";
        }

        long idVisita = visitaServicio.create(visitaDTO);

        switch(usuarioDTO.getTipoUsuario()){
            case "Administrativo":
                administrativoDTO.setUsuario(idUsuario);
                administrativoServicio.create(administrativoDTO);

        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("visita.create.success"));
        return "redirect:/visitas";
    }

        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("usuario.create.success"));
        return "redirect:/usuarios";
    }

    @GetMapping("/edit/{idVisita}")
    public String edit(@PathVariable final Long idVisita, final Model model) {
        //usuarioDTO
        model.addAttribute("visita", visitaServicio.get(idVisita));
        List<ClienteDTO> clientes = clienteServicio.findAll();
        model.addAttribute("clientes" , clientes);

        return "visita/edit";
    }


    @PostMapping("/edit/{idVisita}")
    public String edit(@PathVariable final Long idVisita,
                       @ModelAttribute("visita") @Valid final VisitaDTO visitaDTO,

                       final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        final UsuarioDTO currentUsuarioDTO = usuarioServicio.get(idUsuario);
        if (!bindingResult.hasFieldErrors("nombreUsuario") &&
                !usuarioDTO.getNombreUsuario().equalsIgnoreCase(currentUsuarioDTO.getNombreUsuario()) &&
                usuarioServicio.nombreUsuarioExists(usuarioDTO.getNombreUsuario())) {
            bindingResult.rejectValue("nombreUsuario", "Exists.usuario.nombreUsuario");
        }
        if (bindingResult.hasErrors()) {
            return "usuario/edit";
        }

        usuarioServicio.update(idUsuario, usuarioDTO);

        switch(usuarioDTO.getTipoUsuario()){
            case "Administrativo":
                administrativoDTO.setUsuario(idUsuario);
                long idAdministrativo = usuarioServicio.findById(idUsuario).getUsuarioAdministrativo().getIdAdministrativo();
                administrativoServicio.update(idAdministrativo , administrativoDTO);

                break;
            case "Profesional":
                profesionalDTO.setUsuario(idUsuario);
                long idProfesional = usuarioServicio.findById(idUsuario).getUsuarioProfesionales().getIdProfesional();
                profesionalServicio.update(idProfesional , profesionalDTO);

        visitaServicio.update(idVisita, visitaDTO );

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
