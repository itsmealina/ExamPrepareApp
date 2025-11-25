package app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "practicum_themes")
public class PracticumTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private boolean paid;

    private Integer price; // в рублях

    public PracticumTheme() {}

    public PracticumTheme(String name, String description, boolean paid, Integer price) {
        this.name = name;
        this.description = description;
        this.paid = paid;
        this.price = price;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    @Override
    public String toString() {
        return "PracticumTheme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", paid=" + paid +
                ", price=" + price +
                '}';
    }
}
