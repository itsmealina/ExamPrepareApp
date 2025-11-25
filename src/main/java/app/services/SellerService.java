package app.services;

import app.entities.PracticumTheme;
import app.repositories.PracticumThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private final PracticumThemeRepository themeRepo;

    public SellerService(PracticumThemeRepository themeRepo) {
        this.themeRepo = themeRepo;
    }

    public List<PracticumTheme> getAllThemes() {
        return themeRepo.findAll();
    }

    public PracticumTheme createTheme(String name, boolean paid) {
        PracticumTheme theme = new PracticumTheme();
        theme.setName(name);
        theme.setPaid(paid);
        return themeRepo.save(theme);
    }

    public void deleteTheme(Integer id) {
        themeRepo.deleteById(id);
    }

    public Optional<PracticumTheme> getThemeById(Integer id) {
        return themeRepo.findById(id);
    }
}
