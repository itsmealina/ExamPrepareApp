package app.controllers;

import app.entities.PracticumTheme;
import app.services.SellerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/seller")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("themes", sellerService.getAllThemes());
        return "seller/index";
    }

    @GetMapping("/edit/{id}")
    public String editTheme(@PathVariable Integer id, Model model) {
        model.addAttribute("themes", sellerService.getAllThemes());
        model.addAttribute("editTheme", sellerService.getThemeById(id).orElseThrow());
        return "seller/index";
    }

    @PostMapping("/create")
    public String createTheme(@RequestParam String name,
                              @RequestParam(required = false) boolean paid) {
        sellerService.createTheme(name, paid);
        return "redirect:/seller";
    }

    @PostMapping("/update/{id}")
    public String updateTheme(@PathVariable Integer id,
                              @RequestParam String name,
                              @RequestParam(required = false) boolean paid) {
        PracticumTheme theme = sellerService.getThemeById(id).orElseThrow();
        theme.setName(name);
        theme.setPaid(paid);
        sellerService.createTheme(theme.getName(), theme.isPaid()); // save updated
        return "redirect:/seller";
    }

    @PostMapping("/delete/{id}")
    public String deleteTheme(@PathVariable Integer id) {
        sellerService.deleteTheme(id);
        return "redirect:/seller";
    }
}

