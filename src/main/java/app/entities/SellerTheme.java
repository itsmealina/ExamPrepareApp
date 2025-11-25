package app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "seller_themes")
public class SellerTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean paid;

    private String ownerLogin;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }

    public String getOwnerLogin() { return ownerLogin; }
    public void setOwnerLogin(String ownerLogin) { this.ownerLogin = ownerLogin; }
}
