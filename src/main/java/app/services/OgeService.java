package app.services;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OgeService {

    // ==== RECORDS ====
    public record Theme(Long id, String name) {}
    public record Task(Long id, Long themeId, String question, String correctAnswer, String hint) {}

    private final List<Theme> themes = new ArrayList<>();
    private final Map<Long, Task> tasks = new HashMap<>();

    public OgeService() {
        initThemes();
        initTasks();
    }

    private void initThemes() {
        themes.addAll(List.of(
                new Theme(1L, "Измерение информации"),
                new Theme(2L, "Декодирование информации"),
                new Theme(3L, "Значение логического выражения"),
                new Theme(4L, "Поиск пути в графе"),
                new Theme(5L, "Алгоритм обработки чисел"),
                new Theme(6L, "Программа с условием"),
                new Theme(7L, "Адресация в сети"),
                new Theme(8L, "Множества запросов и операции над ними"),
                new Theme(9L, "Анализ ориентированного графа"),
                new Theme(10L, "Системы счисления")
        ));
    }

    private void initTasks() {
        long idCounter = 1L;

        // === Тема 1: Измерение информации ===
        addTask(idCounter++, 1L, "Сколько бит в 1 байте?", "8", "1 байт = 8 бит");
        addTask(idCounter++, 1L, "Сколько байт в 1 КБ?", "1024", "2¹⁰");
        addTask(idCounter++, 1L, "Сколько информации в слове из 5 букв (алфавит 32 символа)?", "25", "5×log₂(32)=25");

        // === Тема 2: Декодирование информации ===
        addTask(idCounter++, 2L, "Сообщение '0101'₂ → десятичное?", "5", "");
        addTask(idCounter++, 2L, "Сколько символов можно закодировать 3 битами?", "8", "2³");

        // === Тема 3: Логические выражения ===
        addTask(idCounter++, 3L, "NOT (A AND B) равно?", "NOT A OR NOT B", "Закон Де Моргана");
        addTask(idCounter++, 3L, "A → B эквивалентно?", "NOT A OR B", "");

        // === Тема 4: Поиск пути в графе ===
        addTask(idCounter++, 4L, "Кратчайший путь от A до D в графе 4 вершин?", "3", "");
        addTask(idCounter++, 4L, "Сколько вершин графа содержит цикл?", "4", "");

        // === Тема 5: Алгоритмы обработки чисел ===
        addTask(idCounter++, 5L, "Сумма чисел 1, 2, 3?", "6", "");
        addTask(idCounter++, 5L, "Произведение чисел 2,3,4?", "24", "");

        // === Тема 6: Программа с условием ===
        addTask(idCounter++, 6L, "Если x>5, то y=10. x=3, чему y?", "Не изменяется", "");
        addTask(idCounter++, 6L, "Если x<=5, то y=10. x=4, чему y?", "10", "");

        // === Тема 7: Адресация в сети ===
        addTask(idCounter++, 7L, "IP 192.168.1.1 принадлежит сети 192.168.1.0/24?", "Да", "");
        addTask(idCounter++, 7L, "IP 10.0.0.1 принадлежит сети 192.168.0.0/16?", "Нет", "");

        // === Тема 8: Множества и операции ===
        addTask(idCounter++, 8L, "Объединение {1,2} ∪ {2,3}?", "{1,2,3}", "");
        addTask(idCounter++, 8L, "Пересечение {1,2} ∩ {2,3}?", "{2}", "");

        // === Тема 9: Анализ ориентированного графа ===
        addTask(idCounter++, 9L, "Количество исходящих рёбер вершины A?", "2", "");
        addTask(idCounter++, 9L, "Количество входящих рёбер вершины B?", "1", "");

        // === Тема 10: Системы счисления ===
        addTask(idCounter++, 10L, "1010₂ = ?", "10", "");
        addTask(idCounter++, 10L, "17₁₀ = ?", "10001", "");
        addTask(idCounter++, 10L, "FF₁₆ = ?", "255", "");
    }

    private void addTask(Long id, Long themeId, String question, String answer, String hint) {
        tasks.put(id, new Task(id, themeId, question, answer, hint));
    }

    // ==== Методы для контроллера ====
    public List<Theme> getThemes() {
        return themes;
    }

    public Theme getTheme(Long id) {
        return themes.stream().filter(t -> t.id().equals(id)).findFirst().orElse(null);
    }

    public List<Task> getTasksByTheme(Long themeId) {
        return tasks.values().stream()
                .filter(t -> t.themeId().equals(themeId))
                .sorted(Comparator.comparing(Task::id))
                .toList();
    }

    public Task getTask(Long id) {
        return tasks.get(id);
    }

    public int getTaskCount(Long themeId) {
        return getTasksByTheme(themeId).size();
    }

    public Long getNextTaskId(Long themeId, Long cur) {
        var ids = getTasksByTheme(themeId).stream().map(Task::id).toList();
        int idx = ids.indexOf(cur);
        return (idx < ids.size() - 1) ? ids.get(idx + 1) : null;
    }

    public boolean isLastTask(Long themeId, Long taskId) {
        return getNextTaskId(themeId, taskId) == null;
    }
}
