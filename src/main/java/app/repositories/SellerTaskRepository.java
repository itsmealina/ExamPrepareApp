package app.repositories;

import app.entities.SellerTask;
import app.entities.SellerTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SellerTaskRepository extends JpaRepository<SellerTask, Long> {
    List<SellerTask> findByOwnerLogin(String ownerLogin);
    List<SellerTask> findByTheme(SellerTheme theme);
}
