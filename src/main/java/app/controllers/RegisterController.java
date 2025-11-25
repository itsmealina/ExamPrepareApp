package app.controllers;

import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    // Показать страницу регистрации
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // Обработка данных формы регистрации
    @PostMapping("/register")
    public String registerUser(@RequestParam String login,
                               @RequestParam String password,
                               Model model) {
        try {
            userService.register(login, password, "USER");

            model.addAttribute("successMessage", "✅ Регистрация успешна! Теперь войдите в систему.");
            return "login"; // переход на страницу логина после успешной регистрации
        } catch (Exception e) {
            model.addAttribute("errorMessage", "⚠ Ошибка при регистрации: " + e.getMessage());
            return "register";
        }
    }
}
