package app.services;

import app.entities.SellerTheme;
import app.repositories.SellerThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerThemeService {

    private final SellerThemeRepository repo;

    public SellerThemeService(SellerThemeRepository repo) {
        this.repo = repo;
    }

    // Список тем конкретного продавца
    public List<SellerTheme> listForSeller(String login) {
        return repo.findByOwnerLogin(login);
    }

    // Создать новую тему
    public SellerTheme create(String login, String name, boolean paid) {
        SellerTheme theme = new SellerTheme();
        theme.setOwnerLogin(login);
        theme.setName(name);
        theme.setPaid(paid);
        return repo.save(theme);
    }

    // Удалить тему (только если владелец совпадает)
    public void delete(Long id, String login) {
        repo.findById(id).ifPresent(theme -> {
            if (theme.getOwnerLogin().equals(login)) repo.delete(theme);
        });
    }
}
