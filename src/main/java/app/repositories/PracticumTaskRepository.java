package app.repositories;

import app.entities.PracticumTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PracticumTaskRepository extends JpaRepository<PracticumTask, Integer> {
    List<PracticumTask> findByThemeId(Integer themeId);
}
