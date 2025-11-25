package app.entities;

import app.security.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    // Логин для входа
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    // Обычный пароль не сохраняем! Только для временного использования при регистрации.
    @Transient
    private String password;

    // Хэш пароля
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    // Остальные данные пользователь может заполнить позже
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday_date")
    private String birthdayDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;


    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
