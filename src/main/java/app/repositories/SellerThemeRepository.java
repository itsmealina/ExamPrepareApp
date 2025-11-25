package app.repositories;

import app.entities.SellerTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SellerThemeRepository extends JpaRepository<SellerTheme, Long> {
    List<SellerTheme> findByOwnerLogin(String login);
}
