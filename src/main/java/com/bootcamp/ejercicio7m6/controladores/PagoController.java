package com.bootcamp.ejercicio7m6.controladores;

import com.bootcamp.ejercicio7m6.modelos.*;
import com.bootcamp.ejercicio7m6.servicios.*;
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

import java.util.List;

@Controller
@RequestMapping("/pagos")
public class PagoController {
    private final PagoServicio pagoServicio;
    private final ClienteServicio clienteServicio;

    public PagoController(final PagoServicio pagoServicio, ClienteServicio clienteServicio) {
        this.pagoServicio = pagoServicio;
        this.clienteServicio = clienteServicio;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("pagos", pagoServicio.findAll());
        return "pago/list";
    }

    @GetMapping("/add")
    public String showAddForm(@ModelAttribute("pago") final PagoDTO pagoDTO, final Model model){
        List<ClienteDTO> clientes = clienteServicio.findAll();
        model.addAttribute("clientes", clientes);
        return "pago/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("pago") @Valid final PagoDTO pagoDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "pago/add";
        }
        pagoServicio.create(pagoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("pago.create.success"));
        return "redirect:/pagos";

    }

}
