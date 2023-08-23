package com.bootcamp.ejercicio7m6.controladores;

import com.bootcamp.ejercicio7m6.modelos.AdministrativoDTO;
import com.bootcamp.ejercicio7m6.modelos.ClienteDTO;
import com.bootcamp.ejercicio7m6.modelos.ProfesionalDTO;
import com.bootcamp.ejercicio7m6.modelos.UsuarioDTO;
import com.bootcamp.ejercicio7m6.servicios.UsuarioServicio;
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

import java.util.*;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServicio usuarioServicio;

    public UsuarioController(final UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("usuarios", usuarioServicio.findAll());
        return "usuario/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("usuario") final UsuarioDTO usuarioDTO ,
                      @ModelAttribute("usuarioAdministrativo") final AdministrativoDTO cadministrativoDTO ,
                      @ModelAttribute("usuarioProfesional") final ProfesionalDTO profesionalDTO ,
                      @ModelAttribute("usuarioCliente") final ClienteDTO clienteDTO) {
        return "usuario/add";
    }

    @ModelAttribute("tipoUsuarioValues")
    public List<String> tipoUsuarioValues() {
        return Arrays.asList("Administrativo", "Cliente", "Profesional"); // Definir tus valores aquí
    }



    @PostMapping("/add")
    public String add(@ModelAttribute("usuario") @Valid final UsuarioDTO usuarioDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("nombreUsuario") && usuarioServicio.nombreUsuarioExists(usuarioDTO.getNombreUsuario())) {
            bindingResult.rejectValue("nombreUsuario", "Exists.usuario.nombreUsuario");
        }
        if (bindingResult.hasErrors()) {
            return "usuario/add";
        }
        usuarioServicio.create(usuarioDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("usuario.create.success"));
        return "redirect:/usuarios";
    }

    @GetMapping("/edit/{idUsuario}")
    public String edit(@PathVariable final Long idUsuario, final Model model) {
        model.addAttribute("usuario", usuarioServicio.get(idUsuario));
        return "usuario/edit";
    }

    @PostMapping("/edit/{idUsuario}")
    public String edit(@PathVariable final Long idUsuario,
                       @ModelAttribute("usuario") @Valid final UsuarioDTO usuarioDTO,
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
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("usuario.update.success"));
        return "redirect:/usuarios";
    }

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

}
