// src/main/java/app/controllers/GlobalControllerAdvice.java
package app.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addCommonAttributes(Model model,
                                    HttpServletRequest request,
                                    @AuthenticationPrincipal UserDetails principal) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("principalName", principal != null ? principal.getUsername() : null);
    }
}
