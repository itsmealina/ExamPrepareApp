package app.repositories;

import app.entities.PracticumTheme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<PracticumTheme, Integer> {
}
