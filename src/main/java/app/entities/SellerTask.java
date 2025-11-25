package app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seller_tasks")
@Getter
@Setter
public class SellerTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerLogin;
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private SellerTheme theme;
}
