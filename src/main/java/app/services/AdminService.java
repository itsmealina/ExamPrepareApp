package app.services;

import app.entities.PracticumTheme;
import app.entities.Task;
import app.repositories.TaskRepository;
import app.repositories.PracticumThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final PracticumThemeRepository themeRepo;
    private final TaskRepository taskRepo;

    public AdminService(PracticumThemeRepository themeRepo, TaskRepository taskRepo) {
        this.themeRepo = themeRepo;
        this.taskRepo = taskRepo;
    }

    // ----------------------
    // Темы
    // ----------------------
    public List<PracticumTheme> getAllThemes() {
        return themeRepo.findAll();
    }

    public Optional<PracticumTheme> getPracticumTheme(Integer id) {
        return themeRepo.findById(id);
    }

    public PracticumTheme createTheme(String name) {
        PracticumTheme theme = new PracticumTheme();
        theme.setName(name);
        return themeRepo.save(theme);
    }

    public void deleteTheme(Integer id) {
        themeRepo.deleteById(id);
    }

    // ----------------------
    // Задания
    // ----------------------
    public List<Task> getTasksByTheme(PracticumTheme theme) {
        return taskRepo.findByTheme(theme);
    }

    public Task addTask(Integer themeId, String question, String answer, String hint) {
        Task task = new Task();
        PracticumTheme theme = themeRepo.findById(themeId).orElseThrow();
        task.setTheme(theme);
        task.setQuestion(question);
        task.setAnswer(answer);
        task.setHint(hint);
        return taskRepo.save(task);
    }

    public void deleteTask(Integer id) {
        taskRepo.deleteById(id);
    }
}
