package com.bootcamp.ejercicio7m6.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        // Invalidar la sesión actual
        request.logout();

        // Redirigir a la página de inicio o a donde prefieras
        return "redirect:/login?logout";
    }

}
