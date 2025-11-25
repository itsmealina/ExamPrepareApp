package app.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerLogin; // кто положил в корзину

    private Integer themeId; // ссылка на PracticumTheme.id

    private Integer price; // сохраним цену на момент добавления

    private Integer quantity = 1;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOwnerLogin() { return ownerLogin; }
    public void setOwnerLogin(String ownerLogin) { this.ownerLogin = ownerLogin; }

    public Integer getThemeId() { return themeId; }
    public void setThemeId(Integer themeId) { this.themeId = themeId; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
