package app.services;

import app.entities.User;
import app.repositories.UserRepository;
import app.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✔ Регистрация только по логину и паролю
    public void register(String login, String password, String role) {
        User user = new User();
        user.setLogin(login);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole(Role.valueOf(role));
        userRepository.save(user);
    }


    // поиск по логину
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    // для админки
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    // удалить пользователя
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // сохранить профиль
    public User save(User user) {
        return userRepository.save(user);
    }
}
