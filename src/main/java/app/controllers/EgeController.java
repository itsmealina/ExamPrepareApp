package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ege")
public class EgeController {

    @GetMapping
    public String egePage() {
        return "ege";
    }

    @PostMapping("/check")
    public String checkAnswers(@RequestParam String answer1,
                               @RequestParam String answer2,
                               @RequestParam String answer3,
                               Model model) {

        int correct = 0;

        if (answer1.trim().equalsIgnoreCase("Пифагор")) correct++;
        if (answer2.trim().equalsIgnoreCase("1812")) correct++;
        if (answer3.trim().equalsIgnoreCase("3.14")) correct++;

        model.addAttribute("total", 3);
        model.addAttribute("correct", correct);
        model.addAttribute("testName", "ЕГЭ");

        return "result";
    }
}
