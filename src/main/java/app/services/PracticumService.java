package app.services;

import app.entities.PracticumTheme;
import app.entities.PracticumTask;
import app.repositories.PracticumThemeRepository;
import app.repositories.PracticumTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PracticumService {

    private final PracticumThemeRepository themeRepository;
    private final PracticumTaskRepository taskRepository;

    // получить все темы
    public List<PracticumTheme> getAllThemes() {
        return themeRepository.findAll();
    }

    // получить тему по ID (Integer!)
    public PracticumTheme getThemeById(Integer id) {
        return themeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theme not found"));
    }

    // получить задания по теме (Integer!)
    public List<PracticumTask> getTasksByTheme(Integer themeId) {
        return taskRepository.findByThemeId(themeId);
    }

    // получить одно задание
    public PracticumTask getTaskById(Integer taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
}
