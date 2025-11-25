package app.repositories;

import app.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByOwnerLogin(String ownerLogin);
    Optional<CartItem> findByOwnerLoginAndThemeId(String ownerLogin, Integer themeId);
    void deleteByOwnerLoginAndThemeId(String ownerLogin, Integer themeId);
}

