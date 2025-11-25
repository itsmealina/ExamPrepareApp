package app.controllers;

import app.services.PracticumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/practicum")
public class PracticumController {

    private final PracticumService practicumService;

    public PracticumController(PracticumService practicumService) {
        this.practicumService = practicumService;
    }

    @GetMapping
    public String practicumIndex(Model model) {
        model.addAttribute("themes", practicumService.getAllThemes());
        return "practicum/index";
    }

    @GetMapping("/{id}")
    public String showTheme(@PathVariable Integer id, Model model) {
        var theme = practicumService.getThemeById(id);
        var tasks = practicumService.getTasksByTheme(id);

        model.addAttribute("theme", theme);
        model.addAttribute("tasks", tasks);

        return "practicum/theme";
    }

}
