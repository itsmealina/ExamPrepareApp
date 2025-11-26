package app.services;

import app.entities.SellerTask;
import app.repositories.SellerTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerTaskService {

    private final SellerTaskRepository repo;

    public SellerTaskService(SellerTaskRepository repo) { this.repo = repo; }

    public List<SellerTask> listForSeller(String login) { return repo.findByOwnerLogin(login); }

    public SellerTask create(String login, String title, String description) {
        SellerTask t = new SellerTask();
        t.setOwnerLogin(login);
        t.setTitle(title);
        t.setDescription(description);
        return repo.save(t);
    }

    public void save(SellerTask task) { repo.save(task); }

    public void delete(Long id, String login) {
        repo.findById(id).ifPresent(task -> {
            if (task.getOwnerLogin().equals(login)) repo.delete(task);
        });
    }
}
