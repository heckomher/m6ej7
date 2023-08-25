package com.bootcamp.ejercicio7m6.controladores;

import com.bootcamp.ejercicio7m6.entidades.Checklist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checklists")
public class ChecklistController {

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("checklistItem", new Checklist()); // Assuming ChecklistItem is your domain model
        return "add_checklist_item"; // This should match the Thymeleaf template name
    }

    @PostMapping("/save")
    public String saveChecklistItem(Checklist Checklist) {
        // Implement the logic to save the checklistItem to your database or storage
        // Redirect to the checklist list page or any other appropriate page
        return "redirect:/checklists";
    }
}
