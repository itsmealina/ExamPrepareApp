package app.repositories;

import app.entities.Task;
import app.entities.PracticumTheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByTheme(PracticumTheme theme);
}
