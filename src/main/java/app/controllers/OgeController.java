package app.controllers;

import app.services.OgeService;
import app.services.OgeService.Task;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/oge")
public class OgeController {

    private final OgeService ogeService;

    public OgeController(OgeService ogeService) {
        this.ogeService = ogeService;
    }

    @GetMapping
    public String themes(Model model) {
        model.addAttribute("themes", ogeService.getThemes());
        return "oge/themes";
    }

    @GetMapping("/theme/{themeId}")
    public String startTheme(@PathVariable Long themeId, HttpSession session) {
        // Инициализируем сессию
        session.setAttribute("currentTheme", themeId);
        session.setAttribute("answers", new HashMap<Long, String>());
        session.setAttribute("correctCount", 0);

        var tasks = ogeService.getTasksByTheme(themeId);
        if (tasks.isEmpty()) return "redirect:/oge";

        return "redirect:/oge/task/" + tasks.get(0).id();
    }

    @GetMapping("/task/{taskId}")
    public String showTask(@PathVariable Long taskId, HttpSession session, Model model) {
        Long themeId = (Long) session.getAttribute("currentTheme");
        if (themeId == null) return "redirect:/oge";

        Task task = ogeService.getTask(taskId);
        if (task == null) return "redirect:/oge";

        var allTasks = ogeService.getTasksByTheme(themeId);
        int currentIdx = allTasks.indexOf(task) + 1;
        int total = allTasks.size();
        int progress = (int) ((currentIdx * 100.0) / total);

        model.addAttribute("task", task);
        model.addAttribute("currentTask", currentIdx);
        model.addAttribute("totalTasks", total);
        model.addAttribute("currentProgress", progress);
        model.addAttribute("remainingTime", 30);
        model.addAttribute("nextTask", ogeService.getNextTaskId(themeId, taskId));

        return "oge/task";
    }

    @PostMapping("/task/{taskId}")
    public String checkTask(@PathVariable Long taskId,
                            @RequestParam String answer,
                            HttpSession session,
                            Model model) {

        Long themeId = (Long) session.getAttribute("currentTheme");
        if (themeId == null) return "redirect:/oge";

        Task task = ogeService.getTask(taskId);
        if (task == null) return "redirect:/oge";

        @SuppressWarnings("unchecked")
        Map<Long, String> answers = (Map<Long, String>) session.getAttribute("answers");
        answers.put(taskId, answer);

        boolean correct = task.correctAnswer().trim().equalsIgnoreCase(answer.trim());

        if (correct) {
            int cnt = (int) session.getAttribute("correctCount") + 1;
            session.setAttribute("correctCount", cnt);
        }

        var allTasks = ogeService.getTasksByTheme(themeId);
        int currentIdx = allTasks.indexOf(task) + 1;
        int total = allTasks.size();
        int progress = (int) ((currentIdx * 100.0) / total);

        model.addAttribute("task", task);
        model.addAttribute("userAnswer", answer);
        model.addAttribute("correct", correct);
        model.addAttribute("currentTask", currentIdx);
        model.addAttribute("totalTasks", total);
        model.addAttribute("currentProgress", progress);
        model.addAttribute("nextTask", ogeService.getNextTaskId(themeId, taskId));

        return "oge/task-result";
    }

    @GetMapping("/result")
    public String result(HttpSession session, Model model) {
        Long themeId = (Long) session.getAttribute("currentTheme");
        if (themeId == null) return "redirect:/oge";

        int correct = (int) session.getAttribute("correctCount");
        int total = ogeService.getTaskCount(themeId);

        model.addAttribute("theme", ogeService.getTheme(themeId));
        model.addAttribute("correct", correct);
        model.addAttribute("total", total);
        model.addAttribute("percentage", total > 0 ? correct * 100 / total : 0);

        session.removeAttribute("currentTheme");
        session.removeAttribute("answers");
        session.removeAttribute("correctCount");

        return "oge/result";
    }
}
