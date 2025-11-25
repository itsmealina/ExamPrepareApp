// src/main/java/app/controllers/ProfileController.java
package app.controllers;

import app.entities.User;
import app.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) { this.userService = userService; }

    @GetMapping
    public String showProfile(Model model,
                              HttpServletRequest request,
                              @AuthenticationPrincipal UserDetails principal) {

        if (principal == null) return "redirect:/login";

        User user = userService.findByLogin(principal.getUsername()).orElse(null);
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        model.addAttribute("currentUri", request.getRequestURI());
        return "profile";
    }

    @PostMapping("/save")
    public String saveProfile(@ModelAttribute("user") User formUser,
                              @AuthenticationPrincipal UserDetails principal) {

        if (principal == null) return "redirect:/login";
        User user = userService.findByLogin(principal.getUsername()).orElse(null);
        if (user == null) return "redirect:/login";

        user.setFullName(formUser.getFullName());
        user.setEmail(formUser.getEmail());
        user.setBirthdayDate(formUser.getBirthdayDate());
        userService.save(user);

        return "redirect:/profile";
    }
}
