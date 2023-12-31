package com.bootcamp.ejercicio7m6.controladores;

import com.bootcamp.ejercicio7m6.entidades.Usuario;
import com.bootcamp.ejercicio7m6.modelos.AdministrativoDTO;
import com.bootcamp.ejercicio7m6.modelos.ClienteDTO;
import com.bootcamp.ejercicio7m6.modelos.ProfesionalDTO;
import com.bootcamp.ejercicio7m6.modelos.UsuarioDTO;
import com.bootcamp.ejercicio7m6.servicios.AdministrativoServicio;
import com.bootcamp.ejercicio7m6.servicios.ClienteServicio;
import com.bootcamp.ejercicio7m6.servicios.ProfesionalServicio;
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

    private final ClienteServicio clienteServicio;
    private final AdministrativoServicio administrativoServicio;
    private final ProfesionalServicio profesionalServicio;

    public UsuarioController(final UsuarioServicio usuarioServicio, ClienteServicio clienteServicio, AdministrativoServicio administrativoServicio, ProfesionalServicio profesionalServicio) {
        this.usuarioServicio = usuarioServicio;
        this.clienteServicio = clienteServicio;
        this.administrativoServicio = administrativoServicio;
        this.profesionalServicio = profesionalServicio;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("usuarios", usuarioServicio.findAll());
        return "usuario/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("usuario") final UsuarioDTO usuarioDTO ,
                      @ModelAttribute("administrativo") final AdministrativoDTO administrativoDTO ,
                      @ModelAttribute("profesional") final ProfesionalDTO profesionalDTO ,
                      @ModelAttribute("cliente") final ClienteDTO clienteDTO) {
        return "usuario/add";
    }

    @ModelAttribute("tipoUsuarioValues")
    public List<String> tipoUsuarioValues() {
        return Arrays.asList("Administrativo", "Cliente", "Profesional"); // Definir tus valores aquí
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("usuario") @Valid final UsuarioDTO usuarioDTO,
                      @ModelAttribute("cliente") @Valid final ClienteDTO clienteDTO,
                      @ModelAttribute("administrativo") @Valid final AdministrativoDTO administrativoDTO,
                      @ModelAttribute("profesional") @Valid final ProfesionalDTO profesionalDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasFieldErrors("nombreUsuario") && usuarioServicio.nombreUsuarioExists(usuarioDTO.getNombreUsuario())) {
            bindingResult.rejectValue("nombreUsuario", "Exists.usuario.nombreUsuario");
        }
        if (bindingResult.hasErrors()) {
            return "usuario/add";
        }

        long idUsuario = usuarioServicio.create(usuarioDTO);

        switch(usuarioDTO.getTipoUsuario()){
            case "Administrativo":
                administrativoDTO.setUsuario(idUsuario);
                administrativoServicio.create(administrativoDTO);

                break;
            case "Profesional":
                profesionalDTO.setUsuario(idUsuario);
                profesionalServicio.create(profesionalDTO);

                break;
            case "Cliente":
                clienteDTO.setUsuario(idUsuario);
                clienteServicio.create(clienteDTO);

                break;
        }

        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("usuario.create.success"));
        return "redirect:/usuarios";
    }

    @GetMapping("/edit/{idUsuario}")
    public String edit(@PathVariable final Long idUsuario, final Model model) {
        //usuarioDTO
        model.addAttribute("usuario", usuarioServicio.get(idUsuario));
        UsuarioDTO usuario = usuarioServicio.get(idUsuario);


        switch (usuario.getTipoUsuario()){
            case "Administrativo":
                model.addAttribute("cliente", new ClienteDTO());
                model.addAttribute("administrativo", usuarioServicio.findById(idUsuario).getUsuarioAdministrativo());
                model.addAttribute("profesional", new ProfesionalDTO());

                break;
            case "Profesional":
                model.addAttribute("cliente", new ClienteDTO());
                model.addAttribute("administrativo", new AdministrativoDTO());
                model.addAttribute("profesional", usuarioServicio.findById(idUsuario).getUsuarioProfesionales());

                break;
            case "Cliente":
                model.addAttribute("cliente", usuarioServicio.findById(idUsuario).getUsuarioClientes());
                model.addAttribute("administrativo", new AdministrativoDTO());
                model.addAttribute("profesional", new ProfesionalDTO());

                break;

        }

        /*model.addAttribute("cliente", clienteServicio.get(idUsuario));
        model.addAttribute("profesional", profesionalServicio.get(idUsuario));
        model.addAttribute("administrativo", administrativoServicio.get(idUsuario));*/
        return "usuario/edit";
    }

    @PostMapping("/edit/{idUsuario}")
    public String edit(@PathVariable final Long idUsuario,
                       @ModelAttribute("usuario") @Valid final UsuarioDTO usuarioDTO,
                       @ModelAttribute("cliente") @Valid final ClienteDTO clienteDTO,
                       @ModelAttribute("administrativo") @Valid final AdministrativoDTO administrativoDTO,
                       @ModelAttribute("profesional") @Valid final ProfesionalDTO profesionalDTO,

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

                break;
            case "Cliente":
                clienteDTO.setUsuario(idUsuario);
                long idCliente = usuarioServicio.findById(idUsuario).getUsuarioClientes().getIdCliente();
                clienteServicio.update(idCliente , clienteDTO);

                break;
        }



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
