package app.controllers;

import app.entities.PracticumTheme;
import app.entities.Task;
import app.services.AdminService;
import app.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @GetMapping
    public String index() {
        return "admin/index";
    }

    @GetMapping("/themes")
    public String themes(Model model) {
        model.addAttribute("themes", adminService.getAllThemes());
        return "admin/themes";
    }

    @PostMapping("/themes/create")
    public String createTheme(@RequestParam String name) {
        adminService.createTheme(name);
        return "redirect:/admin/themes";
    }

    @PostMapping("/themes/delete/{id}")
    public String deleteTheme(@PathVariable Integer id) {
        adminService.deleteTheme(id);
        return "redirect:/admin/themes";
    }

    @GetMapping("/tasks/{themeId}")
    public String tasks(@PathVariable Integer themeId, Model model) {
        PracticumTheme theme = adminService.getPracticumTheme(themeId).orElseThrow();
        model.addAttribute("theme", theme);
        model.addAttribute("tasks", adminService.getTasksByTheme(theme));
        return "admin/tasks";
    }

    @PostMapping("/tasks/{themeId}/create")
    public String createTask(@PathVariable Integer themeId,
                             @RequestParam String question,
                             @RequestParam String correctAnswer,
                             @RequestParam(required = false) String hint) {
        adminService.addTask(themeId, question, correctAnswer, hint);
        return "redirect:/admin/tasks/" + themeId;
    }

    @PostMapping("/tasks/delete/{themeId}/{id}")
    public String deleteTask(@PathVariable Integer themeId, @PathVariable Integer id) {
        adminService.deleteTask(id);
        return "redirect:/admin/tasks/" + themeId;
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
